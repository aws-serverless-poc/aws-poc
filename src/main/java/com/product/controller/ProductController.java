package com.product.controller;

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
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		productService.createProduct(product);
		LOGGER.info("Product:{} created successfully.");
		return new ResponseEntity<>("Product has been saved successfully.", HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
		productService.updateProduct(product);
		LOGGER.info("Product has been updated successfully.");
		return new ResponseEntity<>("Product has been updated successfully.", HttpStatus.OK);
	}
	
	@GetMapping(value = "/find/{productId}/{sku}")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") String productId, @PathVariable("sku") String sku) {
		Product product = productService.getProduct(productId, sku);
		LOGGER.info("Product:{} found with productId:{}", product, productId);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{productId}/{sku}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("productId") String productId, @PathVariable("sku") String sku) {
		productService.deleteProduct(productId, sku);
		LOGGER.info("Product with productId:{} is deleted successfully.", productId);
		return ResponseEntity.noContent().build();
	}
}