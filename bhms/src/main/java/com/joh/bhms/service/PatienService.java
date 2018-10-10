package com.joh.bhms.service;

import java.util.Date;
import java.util.List;

import com.joh.bhms.model.Patient;

public interface PatienService {

	Iterable<Patient> findAll();

	Patient save(Patient patient);

	Patient findOne(int id);

	Patient update(Patient patient);

	List<Patient> findAllByTimeBetween(Date from, Date to);

}
