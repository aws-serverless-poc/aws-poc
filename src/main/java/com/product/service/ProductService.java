package com.product.service;

import com.product.model.Product;

public interface ProductService {

	void createProduct(Product product);

	void updateProduct(Product product);

	Product getProduct(String productId, String sku);

	void deleteProduct(String productId, String sku);

}
