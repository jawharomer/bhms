package com.joh.bhms.service;

import java.util.List;

import com.joh.bhms.model.VisitPayment;

public interface VisitPaymentService {

	List<VisitPayment> findAllByPatientVisitId(int id);

}
