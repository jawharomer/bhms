package com.joh.bhms.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.bhms.dao.AppointmentDAO;
import com.joh.bhms.model.Appointment;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentDAO appointmentDAO;

	@Override
	public Iterable<Appointment> findAll() {
		return appointmentDAO.findAll();
	}

	@Override
	public List<Appointment> findAllByDateBetween(Date from, Date to) {
		return appointmentDAO.findAllByDateBetween(from, to);
	}

	@Override
	public Appointment save(Appointment appointment) {
		return appointmentDAO.save(appointment);
	}

	@Override
	public Appointment findOne(int id) {
		return appointmentDAO.findOne(id);
	}

	@Override
	public Appointment update(Appointment appointment) {
		if (appointment.getId() == null)
			throw new EntityNotFoundException();
		return appointmentDAO.save(appointment);
	}

	@Override
	public void delete(int id) {
		appointmentDAO.delete(id);
	}

}
