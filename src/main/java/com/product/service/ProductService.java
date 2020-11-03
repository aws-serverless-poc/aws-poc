package com.product.service;

import java.util.Collection;

import com.product.model.Product;

public interface ProductService {

	Product createProduct(Product product);

	Product updateProduct(Long productId, Product product);

	Collection<Product> getProductList();

	Product getProductById(Long productId);

	void deleteProduct(Long productId);

}
