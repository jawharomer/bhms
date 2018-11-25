package com.joh.bhms.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joh.bhms.domain.model.DoctorIncomeD;
import com.joh.bhms.model.Doctor;
import com.joh.bhms.model.PatientVisit;
import com.joh.bhms.model.VisitPayment;
import com.joh.bhms.service.DoctorService;
import com.joh.bhms.service.PatientVisitService;
import com.joh.bhms.service.ReportService;
import com.joh.bhms.service.VisitPaymentService;

@Controller
@RequestMapping(path = "/incomes")
public class IncomeController {

	private static final Logger logger = Logger.getLogger(IncomeController.class);

	@Autowired
	private ReportService reportService;

	@GetMapping(path = "/doctors")
	public String getAllDoctorIncome(@RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getAllDoctorIncome->fired");

		logger.info("from=" + from);
		logger.info("to=" + to);

		List<DoctorIncomeD> doctorIncomeDs = reportService.findAllDoctorIncome(from, to);

		logger.info("doctorIncomeDs=" + doctorIncomeDs);
		
		double productUsedCost = reportService.findProductUsedCost(from, to);
		logger.info("productUsedCost="+productUsedCost);
		
		model.addAttribute("productUsedCost",productUsedCost);

		model.addAttribute("doctorIncomeDs", doctorIncomeDs);

		model.addAttribute("from", from);
		model.addAttribute("to", to);

		return "doctorIncomes";
	}
}
