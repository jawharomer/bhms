package com.joh.bhms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.joh.bhms.model.Appointment;

public interface AppointmentDAO extends CrudRepository<Appointment, Integer> {
	List<Appointment> findAllByDateBetween(Date from, Date to);
}
