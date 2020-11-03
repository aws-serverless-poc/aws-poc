package com.product.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.model.Product;
import com.product.service.ProductService;

@RestController
@RequestMapping("/rest/api/products")
public class ProductController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;
	
	@PostMapping("/create")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product productObj = productService.createProduct(product);
		LOGGER.info("Product:{} created successfully.", productObj);
		return new ResponseEntity<>(productObj, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId, @RequestBody Product product) {
		Product updatedProduct = productService.updateProduct(productId, product);
		LOGGER.info("Product:{} updated successfully.", updatedProduct.toString());
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}
	
	@GetMapping(value = "/find/productId")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId) {
		Product product = productService.getProductById(productId);
		LOGGER.info("Product:{} found with productId:{}", product, productId);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping(value = "/productlist")
	public Collection<Product> getProduct() {
		Collection<Product> productList = productService.getProductList();
		LOGGER.info("Product list size:{}", productList.size());
		return productList;
	}

	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId) {
		productService.deleteProduct(productId);
		LOGGER.info("Product with productId:{} is deleted successfully.", productId);
		return ResponseEntity.noContent().build();
	}
}