package com.joh.bhms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.joh.bhms.model.Patient;

public interface PatientDAO extends CrudRepository<Patient, Integer> {

	Iterable<Patient> findAllByOrderByIdDesc();

	List<Patient> findAllByTimeBetween(Date from, Date to);

}
