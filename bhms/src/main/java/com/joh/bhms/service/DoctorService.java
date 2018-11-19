package com.joh.bhms.service;

import java.util.List;

import com.joh.bhms.model.Doctor;

public interface DoctorService {

	Iterable<Doctor> findAll();

	void delete(int id);

	Doctor update(Doctor doctor);

	Doctor save(Doctor doctor);

	Doctor findOne(int id);

	List<Doctor> findAllExceptAdmin();

}
