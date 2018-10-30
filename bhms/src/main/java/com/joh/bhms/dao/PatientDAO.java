package com.joh.bhms.dao;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.joh.bhms.model.Patient;

public interface PatientDAO extends CrudRepository<Patient, Integer> {
	Iterable<Patient> findAllByTimeBetweenOrderByTimeDesc(Date from, Date to);
}
