package com.joh.bhms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.joh.bhms.model.VisitPayment;

public interface VisitPaymentDAO extends CrudRepository<VisitPayment, Integer> {
	List<VisitPayment> findAllByPatientVisitId(int id);

	@Query(value = "SELECT * FROM VISIT_PAYMENTS WHERE I_PATIENT_VISIT=?1 AND PAYMENT_TIME BETWEEN CURDATE() AND CURDATE()+INTERVAL 1 DAY", nativeQuery = true)
	List<VisitPayment> findAllAtToday(int patientVisitId);

	@Query("SELECT VP FROM VisitPayment VP JOIN VP.patientDoctors PD JOIN PD.doctor D WHERE D.id=?1 ")
	List<VisitPayment> findAllByDoctorId(int id);

}
