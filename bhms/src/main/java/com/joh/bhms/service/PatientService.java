package com.joh.bhms.service;

import java.util.Date;

import com.joh.bhms.model.Patient;

public interface PatientService {

	Iterable<Patient> findAll(Date from, Date to);

	Patient save(Patient patient);

	Patient findOne(int id);

	void delete(int id);

}
