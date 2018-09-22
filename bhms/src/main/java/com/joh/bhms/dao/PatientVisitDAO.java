package com.joh.bhms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.bhms.model.PatientVisit;

public interface PatientVisitDAO extends CrudRepository<PatientVisit, Integer> {

}
