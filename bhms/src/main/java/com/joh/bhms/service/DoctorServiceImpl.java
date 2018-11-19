package com.joh.bhms.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.bhms.dao.DoctorDAO;
import com.joh.bhms.model.Doctor;
import com.joh.bhms.model.Operation;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorDAO doctorDAO;

	@Override
	public Iterable<Doctor> findAll() {
		return doctorDAO.findAll();
	}

	@Override
	public List<Doctor> findAllExceptAdmin() {
		return doctorDAO.findAllExceptAdmin();
	}

	@Override
	public Doctor findOne(int id) {
		return doctorDAO.findOne(id);
	}

	@Override
	public Doctor save(Doctor doctor) {
		return doctorDAO.save(doctor);
	}

	@Override
	public Doctor update(Doctor doctor) {
		if (doctorDAO.findOne(doctor.getId()) == null)
			throw new EntityNotFoundException();
		return doctorDAO.save(doctor);
	}

	@Override
	public void delete(int id) {
		doctorDAO.delete(id);
	}

}
