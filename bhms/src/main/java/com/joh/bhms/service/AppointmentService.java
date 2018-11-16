package com.joh.bhms.service;

import java.util.Date;
import java.util.List;

import com.joh.bhms.model.Appointment;

public interface AppointmentService {

	Iterable<Appointment> findAll();

	List<Appointment> findAllByDateBetween(Date from, Date to);

	Appointment save(Appointment appointment);

	Appointment findOne(int id);

	Appointment update(Appointment appointment);

	void delete(int id);

}
