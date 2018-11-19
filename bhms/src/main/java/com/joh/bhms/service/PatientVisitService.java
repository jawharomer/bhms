package com.joh.bhms.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.joh.bhms.model.PatientProductUsed;
import com.joh.bhms.model.PatientVisit;

public interface PatientVisitService {

	PatientVisit save(PatientVisit patientVisit);

	PatientVisit findOne(int id);

	PatientVisit update(PatientVisit patientVisit);

	void addAttachedFile(int id, MultipartFile file) throws IOException;

	void deleteAttachedFile(int id, int attachedFileId);

	Iterable<PatientVisit> findAllByTimeBetween(Date from, Date to);

	void addPatientProductUsed(int id, PatientProductUsed patientProductUsed);

	void deletePatientProductUsed(int id, int productUsedId);

	Iterable<PatientVisit> findAllByNextSessionLessThanEqual(Date to);

	List<PatientVisit> findAllByPatientId(int id);

	void delete(int id);

}
