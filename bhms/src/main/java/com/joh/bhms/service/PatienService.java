package com.joh.bhms.service;

import com.joh.bhms.model.Patient;

public interface PatienService {

	Iterable<Patient> findAll();

	Patient save(Patient patient);

}
