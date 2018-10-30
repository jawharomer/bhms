package com.joh.bhms.service;

import com.joh.bhms.model.Product;

public interface ProductService {

	Product findByCode(String code);

	Product save(Product product);

}
