package com.joh.bhms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.bhms.dao.ProductCategoryDAO;
import com.joh.bhms.model.ProductCategory;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryDAO productCategoryDAO;

	@Override
	public Iterable<ProductCategory> findAll() {
		return productCategoryDAO.findAll();
	}

	@Override
	public ProductCategory save(ProductCategory productCategory) {
		return productCategoryDAO.save(productCategory);
	}

	@Override
	public ProductCategory findOne(int id) {
		return productCategoryDAO.findOne(id);
	}

	@Override
	public void delete(int id) {
		productCategoryDAO.delete(id);
	}

}
