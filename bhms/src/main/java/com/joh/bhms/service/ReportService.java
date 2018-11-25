package com.joh.bhms.service;

import java.util.Date;
import java.util.List;

import com.joh.bhms.domain.model.DoctorIncomeD;
import com.joh.bhms.domain.model.NotificationD;

public interface ReportService {

	List<NotificationD> findAdminNotifications();

	List<String> findAllChronicDisease();

	List<String> findAllExamination();


	List<DoctorIncomeD> findAllDoctorIncome(Date from, Date to);

	double findProductUsedCost(Date from, Date to);

}
