package com.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.dynamodb.DynamoDBRepository;
import com.product.model.Product;
import com.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private DynamoDBRepository repository;
	
	@Override
	public void createProduct(Product product) {
		repository.save(product);
	}

	@Override
	public void updateProduct(Product product) {
		repository.update(product);
	}

	@Override
	public Product getProduct(String productId, String sku) {
		return repository.getProduct(productId, sku);
	}

	@Override
	public void deleteProduct(String productId, String sku) {
		repository.delete(productId, sku);
	}

}
