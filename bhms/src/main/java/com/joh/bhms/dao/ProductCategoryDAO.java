package com.joh.bhms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.bhms.model.ProductCategory;

public interface ProductCategoryDAO extends CrudRepository<ProductCategory, Integer> {

}
