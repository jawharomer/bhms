package com.joh.bhms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

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

}
