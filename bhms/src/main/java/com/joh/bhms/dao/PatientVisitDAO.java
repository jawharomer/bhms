package com.joh.bhms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.joh.bhms.model.PatientVisit;

public interface PatientVisitDAO extends CrudRepository<PatientVisit, Integer> {
	List<PatientVisit> findAllByTimeBetween(Date from, Date to);

	List<PatientVisit> findAllByNextSessionLessThanEqual(Date to);

	List<PatientVisit> findAllByPatientId(int id);

}