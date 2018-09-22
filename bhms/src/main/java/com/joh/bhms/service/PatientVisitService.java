package com.joh.bhms.service;

import com.joh.bhms.model.PatientVisit;

public interface PatientVisitService {

	PatientVisit save(PatientVisit patientVisit);

	Iterable<PatientVisit> findAll();

}
