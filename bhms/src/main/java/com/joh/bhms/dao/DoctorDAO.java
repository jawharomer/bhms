package com.joh.bhms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.joh.bhms.model.Doctor;

public interface DoctorDAO extends CrudRepository<Doctor, Integer> {
	
	@Query("SELECT d FROM Doctor d WHERE d.id!=1")
	List<Doctor> findAllExceptAdmin();
}
