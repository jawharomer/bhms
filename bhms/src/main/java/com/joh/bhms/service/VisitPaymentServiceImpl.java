package com.joh.bhms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.bhms.dao.VisitPaymentDAO;
import com.joh.bhms.model.VisitPayment;

@Service
public class VisitPaymentServiceImpl implements VisitPaymentService {
	@Autowired
	private VisitPaymentDAO visitPaymentDAO;

	@Override
	public List<VisitPayment> findAllByPatientVisitId(int id) {
		return visitPaymentDAO.findAllByPatientVisitId(id);
	}
	
	@Override
	public VisitPayment save(VisitPayment visitPayment) {
		return visitPaymentDAO.save(visitPayment);
	}

}
