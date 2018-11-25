package com.joh.bhms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.joh.bhms.domain.model.DoctorIncomeD;
import com.joh.bhms.domain.model.NotificationD;
import com.joh.bhms.domain.model.NotificationD.NotificationType;

@Component
public class ReportDAO {
	@PersistenceContext
	private EntityManager em;

	public List<NotificationD> findAdminNotifications() {

		List<NotificationD> notificationDs = new ArrayList<>();

		// Notification-1

		Query query = em
				.createNativeQuery("SELECT IFNULL(COUNT(*),0) FROM PATIENTS WHERE DATE(ENROLLMENT_TIME)=CURDATE();");

		Object totalPatientResult = query.getSingleResult();

		int totalPatient = 0;
		if (totalPatientResult != null)
			totalPatient = Integer.parseInt("" + totalPatientResult);

		//
		NotificationD not1 = new NotificationD();
		not1.setTitle("Total Today Patient");
		not1.setEtc("" + totalPatient);
		not1.setMessage("Total Number of patients visit in today");

		not1.setNotificationType(NotificationType.INFO);

		notificationDs.add(not1);

		// Notification-2

		query = em.createNativeQuery(
				"SELECT IFNULL(SUM(PAYMENT_AMOUNT),0) FROM  VISIT_PAYMENTS WHERE DATE(PAYMENT_TIME)=CURDATE();");

		Object toatlTodayPaymentResult = query.getSingleResult();

		double totalTodayPayment = 0;

		if (toatlTodayPaymentResult != null)
			totalTodayPayment = Double.parseDouble("" + toatlTodayPaymentResult);

		//
		NotificationD not2 = new NotificationD();
		not2.setTitle("Today Total  Payment");
		not2.setEtc("" + totalTodayPayment);
		not2.setMessage("Total today payment amount");

		not2.setNotificationType(NotificationType.INFO);

		notificationDs.add(not2);

		// Notification-2

		query = em
				.createNativeQuery("SELECT COUNT(*) FROM PATIENT_VISITS	WHERE NEXT_SESSION<=CURDATE()-INTERVAL 2 DAY;");

		Object totalNextSessionResult = query.getSingleResult();

		Integer totalNextSession = 0;

		if (totalNextSessionResult != null)
			totalNextSession = Integer.parseInt("" + totalNextSessionResult);

		//
		NotificationD not3 = new NotificationD();
		not3.setTitle("Total number of patient");
		not3.setEtc("" + totalNextSession);
		not3.setMessage("Total number of patient should visit in next 2 day");

		not3.setNotificationType(NotificationType.INFO);

		notificationDs.add(not3);

		return notificationDs;

	}

	public List<String> findAllChronicDisease() {
		List<String> chronicDiseases = new ArrayList<>();

		Query query = em.createNativeQuery("SELECT DISTINCT DISEASE_NAME FROM CHRONIC_DISEASES ORDER BY DISEASE_NAME;");

		List<String> totalChronicDisease = query.getResultList();
		chronicDiseases.addAll(totalChronicDisease);
		return chronicDiseases;
	}

	public List<String> findAllExamination() {
		List<String> examinations = new ArrayList<>();

		Query query = em.createNativeQuery("SELECT  DISTINCT EXAMINATION_NAME FROM EXAMINATIONS;");

		List<String> totalExaminations = query.getResultList();
		examinations.addAll(totalExaminations);
		return totalExaminations;
	}

	public List<DoctorIncomeD> findAllDoctorIncome(Date from, Date to) {
		List<DoctorIncomeD> doctorIncomeDs = new ArrayList<>();

		Query query = em.createNativeQuery("SELECT I_DOCTOR,FULL_NAME,SUM(RATIO*TOTAL_INCOME) INCOME FROM\n"
				+ "VISIT_PAYMENT_PATIENT_DOCTORS VPPD\n" + "INNER JOIN \n"
				+ "(SELECT I_VISIT_PAYMENT,IFNULL(SUM(PAYMENT_AMOUNT),0)-IFNULL(SUM(COST),0)  TOTAL_INCOME FROM VISIT_PAYMENTS VP\n"
				+ "INNER JOIN PATIENT_VISITS PV USING(I_PATIENT_VISIT)\n"
				+ "LEFT OUTER JOIN PATIENT_PRODUCT_USED PDU ON PV.I_PATIENT_VISIT=PDU.I_PATIENT_VISIT\n"
				+ "AND DATE(PAYMENT_TIME)=DATE(PATIENT_PRODUCT_USED_TIME)\n"
				+ "WHERE PAYMENT_TIME BETWEEN :from AND :to\n" + "GROUP BY I_VISIT_PAYMENT) VT\n"
				+ "USING (I_VISIT_PAYMENT)\n" + "INNER JOIN PATIENT_DOCTORS PD  USING(I_PATIENT_DOCTOR)\n"
				+ "INNER JOIN DOCTORS USING(I_DOCTOR)\n" + "GROUP BY I_DOCTOR;");

		query.setParameter("from", from, TemporalType.DATE);
		query.setParameter("to", to, TemporalType.DATE);

		List<Object[]> resultRows = query.getResultList();

		for (Object columns[] : resultRows) {
			DoctorIncomeD doctorIncomeD = new DoctorIncomeD();

			doctorIncomeD.setDoctorId("" + columns[0]);
			doctorIncomeD.setFullName("" + columns[1]);
			doctorIncomeD.setIncome(Double.parseDouble("" + columns[2]));
			doctorIncomeDs.add(doctorIncomeD);

		}
		return doctorIncomeDs;
	}

	public double findProductUsedCost(Date from, Date to) {

		Query query = em.createNativeQuery("SELECT IFNULL(SUM(COST),0) FROM \n" + "VISIT_PAYMENTS VP\n"
				+ "INNER JOIN PATIENT_VISITS PV USING(I_PATIENT_VISIT)\n" + "INNER JOIN \n"
				+ "PATIENT_PRODUCT_USED PDO USING(I_PATIENT_VISIT)\n" + "WHERE PAYMENT_TIME BETWEEN :from AND :to ");

		query.setParameter("from", from, TemporalType.DATE);
		query.setParameter("to", to, TemporalType.DATE);
		return Double.parseDouble("" + query.getSingleResult());
	}

}
