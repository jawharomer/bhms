package com.joh.bhms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.bhms.controller.ReportDAO;
import com.joh.bhms.domain.model.DoctorIncomeD;
import com.joh.bhms.domain.model.NotificationD;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportDAO reportDAO;
	
	
	@Override
	public List<NotificationD> findAdminNotifications() {
		return reportDAO.findAdminNotifications();
	}
	
	@Override
	public List<String> findAllChronicDisease() {
		return reportDAO.findAllChronicDisease();
	}
	
	@Override
	public List<String> findAllExamination() {
		return reportDAO.findAllExamination();
	}
	

	@Override
	public List<DoctorIncomeD> findAllDoctorIncome(Date from, Date to) {
		return reportDAO.findAllDoctorIncome(from, to);
	}
	
	@Override
	public double findProductUsedCost(Date from, Date to) {
		return reportDAO.findProductUsedCost(from, to);
	}


	
	
}
