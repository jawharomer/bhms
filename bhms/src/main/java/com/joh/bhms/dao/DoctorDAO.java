package com.joh.bhms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.bhms.model.Doctor;

public interface DoctorDAO extends CrudRepository<Doctor, Integer> {

}
