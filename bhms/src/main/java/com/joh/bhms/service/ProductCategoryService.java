package com.joh.bhms.service;

import com.joh.bhms.model.ProductCategory;

public interface ProductCategoryService {

	ProductCategory save(ProductCategory productCategory);

	void delete(int id);

	ProductCategory findOne(int id);

	Iterable<ProductCategory> findAll();

}
