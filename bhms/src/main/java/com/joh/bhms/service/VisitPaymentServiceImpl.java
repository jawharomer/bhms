package com.joh.bhms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.bhms.dao.PatientVisitDAO;
import com.joh.bhms.dao.VisitPaymentDAO;
import com.joh.bhms.model.PatientDoctor;
import com.joh.bhms.model.PatientVisit;
import com.joh.bhms.model.VisitPayment;

@Service
public class VisitPaymentServiceImpl implements VisitPaymentService {
	@Autowired
	private VisitPaymentDAO visitPaymentDAO;

	@Autowired
	private PatientVisitDAO patientVisitDAO;

	@Override
	public List<VisitPayment> findAllByPatientVisitId(int id) {
		return visitPaymentDAO.findAllByPatientVisitId(id);
	}

	@Override
	public VisitPayment save(VisitPayment visitPayment) {
		PatientVisit patientVisit = patientVisitDAO.findOne(visitPayment.getPatientVisit().getId());

		// Keep All current doctors and make it new Array
		visitPayment.setPatientDoctors(new ArrayList<>(patientVisit.getPatientDoctors()));
		return visitPaymentDAO.save(visitPayment);
	}

	@Override
	public List<VisitPayment> findAllByDoctorId(int id) {
		return visitPaymentDAO.findAllByDoctorId(id);
	}

	@Override
	public void delete(int id) {
		visitPaymentDAO.delete(id);
	}

}
