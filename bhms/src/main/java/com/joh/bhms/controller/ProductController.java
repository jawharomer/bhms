package com.joh.bhms.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joh.bhms.model.Product;
import com.joh.bhms.service.ProductService;

@Controller()
@RequestMapping(path = "/products")
public class ProductController {

	private static final Logger logger = Logger.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@GetMapping(path = "/code/{code}")
	@ResponseBody
	public Product getProductByCode(@PathVariable String code) {
		logger.info("getProductByCode->fired");
		logger.info("code=" + code);

		Product product = productService.findByCode(code);
		logger.info("product=" + product);

		return product;
	}
}
