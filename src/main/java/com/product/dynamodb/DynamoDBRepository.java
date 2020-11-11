package com.product.dynamodb;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.product.model.Product;

@Repository
public class DynamoDBRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDBRepository.class);

	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	public void save(Product product) {
		dynamoDBMapper.save(product);
		LOGGER.info("Product saved successfully.");
	}

	public Product getProduct(String productId, String sku) {
		LOGGER.info("Fetching product with productId:{}", productId);
		return dynamoDBMapper.load(Product.class, productId, sku);
	}

	public void update(Product product) {
		try {
			dynamoDBMapper.save(product, buildDynamoDBSaveExcpession(product));
		} catch (ConditionalCheckFailedException exception) {
			LOGGER.error("Exception:{} is occured while updating product with productId:{}", exception,
					product.getProductId());
		}
	}

	public void delete(String productId, String sku) {
		Product product = getProduct(productId, sku);
		if (product != null) {
			dynamoDBMapper.delete(product);
			LOGGER.info("Product is deleted with productId:{}", product.getProductId());
		} else {
			LOGGER.error("Exception is occured while deleting product with productId:{}", productId);
			throw new RuntimeException("No product is found to delete.");
		}
	}

	private DynamoDBSaveExpression buildDynamoDBSaveExcpession(Product product) {
		DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
		Map<String, ExpectedAttributeValue> expected = new HashMap<>();
		expected.put("productId", new ExpectedAttributeValue(new AttributeValue(product.getProductId()))
				.withComparisonOperator(ComparisonOperator.EQ));
		saveExpression.setExpected(expected);
		return saveExpression;
	}
}
