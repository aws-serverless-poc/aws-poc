package com.product.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.product.model.Product;

@Component
public class ProductUtil {
	  public static Map<Long, Product> productMap = new HashMap<>();
	   static {
	      Product honey = new Product();
	      honey.setId(1L);
	      honey.setName("Honey");
	      honey.setSku("12345678");
	      productMap.put(honey.getId(), honey);
	      
	      Product almond = new Product();
	      almond.setId(2L);
	      almond.setName("Almond");
	      almond.setSku("556624242");
	      productMap.put(almond.getId(), almond);
	      
	      Product cashew = new Product();
	      cashew.setId(3L);
	      cashew.setName("Cashew");
	      cashew.setSku("999999999");
	      
	      productMap.put(cashew.getId(), cashew);
	   }
}
