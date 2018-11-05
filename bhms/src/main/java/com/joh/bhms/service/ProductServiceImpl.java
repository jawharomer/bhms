package com.joh.bhms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.bhms.dao.ProductDAO;
import com.joh.bhms.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;

	@Override
	public Product findByCode(String code) {
		return productDAO.findByCode(code);
	}

	@Override
	public Product save(Product product) {
		return productDAO.save(product);
	}

	@Override
	public Iterable<Product> findAll() {
		return productDAO.findAll();
	}

}
