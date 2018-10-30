package com.joh.bhms.service;

import com.joh.bhms.model.Vendor;

public interface VendorService {

	Iterable<Vendor> findAll();

	void delete(int id);

	Vendor findOne(int id);

	Vendor save(Vendor vendor);

}
