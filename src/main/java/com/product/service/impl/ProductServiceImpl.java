package com.product.service.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.product.model.Product;
import com.product.service.ProductService;
import com.product.util.ProductUtil;

@Service
public class ProductServiceImpl implements ProductService {

	@Override
	public Product createProduct(Product product) {
		return ProductUtil.productMap.put(product.getId(), product);
	}

	@Override
	public Product updateProduct(Long productId, Product product) {
		ProductUtil.productMap.remove(productId);
		product.setId(productId);
		return ProductUtil.productMap.put(productId, product);
	}

	@Override
	public Collection<Product> getProductList() {
		return ProductUtil.productMap.values();
	}

	@Override
	public Product getProductById(Long productId) {
		return ProductUtil.productMap.get(productId);
	}

	@Override
	public void deleteProduct(Long productId) {
		ProductUtil.productMap.remove(productId);

	}

}
