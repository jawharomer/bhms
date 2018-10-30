package com.joh.bhms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.bhms.model.Product;


public interface ProductDAO extends CrudRepository<Product, Integer> {
Product findByCode(String code);
}
