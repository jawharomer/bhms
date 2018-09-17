package com.joh.bhms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.bhms.dao.PatientDAO;
import com.joh.bhms.dao.VisitReferenceDAO;
import com.joh.bhms.model.Patient;
import com.joh.bhms.model.VisitReference;

@Service
public class VisitReferenceServiceImpl implements VisitReferenceService {

	@Autowired
	private VisitReferenceDAO visitReferenceDAO;

	@Override
	public Iterable<VisitReference> findAll() {
		return visitReferenceDAO.findAll();
	}

}
