package com.joh.bhms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.bhms.dao.PatientDAO;
import com.joh.bhms.model.Patient;

@Service
public class PatientServiceImpl implements PatienService {

	@Autowired
	private PatientDAO patientDAO;

	@Override
	public Iterable<Patient> findAll() {
		return patientDAO.findAllByOrderByIdDesc();
	}

	@Override
	public List<Patient> findAllByTimeBetween(Date from, Date to) {
		return patientDAO.findAllByTimeBetween(from, to);
	}

	@Override
	public Patient save(Patient patient) {
		return patientDAO.save(patient);
	}

	@Override
	public Patient findOne(int id) {
		return patientDAO.findOne(id);
	}

	@Override
	public Patient update(Patient patient) {
		return patientDAO.save(patient);
	}

}
