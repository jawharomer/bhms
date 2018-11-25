package com.joh.bhms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.bhms.dao.PatientDAO;
import com.joh.bhms.exception.CusDataIntegrityViolationException;
import com.joh.bhms.model.Patient;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientDAO patientDAO;

	@Override
	public Iterable<Patient> findAll(Date from, Date to) {
		return patientDAO.findAllByTimeBetweenOrderByTimeDesc(from, to);
	}

	@Override
	public Patient save(Patient patient) {
		try {
			return patientDAO.save(patient);
		} catch (Exception e) {
			throw new CusDataIntegrityViolationException("This patient is already exists");
		}
	}

	@Override
	public Patient findOne(int id) {
		return patientDAO.findOne(id);
	}

	@Override
	public void delete(int id) {
		patientDAO.delete(id);
	}
}
