package com.joh.bhms.service;

import java.util.List;

import com.joh.bhms.model.VisitPayment;

public interface VisitPaymentService {

	List<VisitPayment> findAllByPatientVisitId(int id);

	VisitPayment save(VisitPayment visitPayment);

	List<VisitPayment> findAllByDoctorId(int id);

}
