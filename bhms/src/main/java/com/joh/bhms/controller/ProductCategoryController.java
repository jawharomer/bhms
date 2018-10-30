package com.joh.bhms.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joh.bhms.model.ProductCategory;
import com.joh.bhms.service.ProductCategoryService;
import com.joh.bhms.validator.ProductCategoryValidation;

@Controller()
@RequestMapping(path = "/productCategories")
public class ProductCategoryController {

	private static final Logger logger = Logger.getLogger(ProductCategoryController.class);

	@Autowired
	private ProductCategoryService productCategoryService;

	@GetMapping()
	public String getAllProductCategory(Model model) {
		logger.info("getAllProductCategory->fired");
		model.addAttribute("productCategories", productCategoryService.findAll());

		return "productCategories";
	}

	@GetMapping(path = "/add")
	public String getAddingProductCategory(Model model) {
		logger.info("getAddingProductCategory->fired");

		model.addAttribute("productCategory", new ProductCategory());

		return "productCategory/addProductCategory";
	}

	@PostMapping(path = "/add")
	public String addProductCategory(@RequestBody @Valid ProductCategory productCategory, BindingResult result,
			Model model) {
		logger.info("addProductCategory->fired");

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {

			model.addAttribute("productCategory", productCategory);
			return "productCategory/addProductCategory";
		} else {
			productCategoryService.save(productCategory);
			return "success";
		}

	}

	@GetMapping(path = "/edit/{id}")
	public String getEditingProductCategory(@PathVariable int id, Model model) {
		logger.info("getEditingProductCategory->fired");
		logger.info("productCategoryId=" + id);

		ProductCategory productCategory = productCategoryService.findOne(id);
		logger.info("productCategory=" + productCategory);

		model.addAttribute("productCategory", productCategory);

		return "productCategory/editProductCategory";
	}

	@PostMapping(path = "/edit")
	public String editProductCategory(
			@RequestBody @Validated(ProductCategoryValidation.Insert.class) ProductCategory productCategory,
			BindingResult result, Model model) {
		logger.info("editProductCategory->fired");

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {

			model.addAttribute("productCategory", productCategory);
			return "productCategory/editProductCategory";
		} else {
			productCategoryService.save(productCategory);
			return "success";
		}

	}

	@PostMapping(path = "/delete/{id}")
	public String deleteProductCategory(@PathVariable int id) {
		logger.info("deleteProductCategory->fired");
		logger.info("productCategoryId=" + id);
		productCategoryService.delete(id);
		return "success";
	}
}
