package com.joh.bhms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.bhms.model.PatientOperation;

public interface PatientOperationDAO extends CrudRepository<PatientOperation, Integer> {

}
