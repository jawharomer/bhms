package com.joh.bhms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.bhms.dao.PatientVisitDAO;
import com.joh.bhms.model.PatientVisit;

@Service
public class PatientVisitServiceImpl implements PatientVisitService {
	@Autowired
	private PatientVisitDAO patientVisitDAO;

	@Override
	public Iterable<PatientVisit> findAll() {
		return patientVisitDAO.findAll();
	}

	@Override
	public PatientVisit save(PatientVisit patientVisit) {
		return patientVisitDAO.save(patientVisit);
	}

}
