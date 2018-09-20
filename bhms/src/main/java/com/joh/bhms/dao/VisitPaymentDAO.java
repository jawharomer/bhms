package com.joh.bhms.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.joh.bhms.model.VisitPayment;

public interface VisitPaymentDAO extends CrudRepository<VisitPayment, Integer> {
	List<VisitPayment> findAllByPatientVisitId(int id);
}
