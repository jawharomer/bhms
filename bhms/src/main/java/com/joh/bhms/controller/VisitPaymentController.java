package com.joh.bhms.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joh.bhms.model.PatientVisit;
import com.joh.bhms.model.VisitPayment;
import com.joh.bhms.service.VisitPaymentService;

@Controller
@RequestMapping(path = "/visitPayments")
public class VisitPaymentController {

	private static final Logger logger = Logger.getLogger(VisitPaymentController.class);

	@Autowired
	private VisitPaymentService visitPaymentService;

	@GetMapping(path = "/patientVisit/{id}")
	public String getAllVisitPatientPayment(@PathVariable int id, Model model) {
		logger.info("getAllVisitPatientPayment->fired");

		List<VisitPayment> visitPayments = visitPaymentService.findAllByPatientVisitId(id);

		model.addAttribute("visitPayments", visitPayments);

		return "patientVisitPayments";
	}

	@GetMapping(path = "/add/{id}")
	public String getAddingVisitPayment(@PathVariable int id, Model model) {
		logger.info("getAddingVisitPayment->fired");

		logger.info("patientVisitId=" + id);

		PatientVisit patientVisit = new PatientVisit();
		patientVisit.setId(id);

		VisitPayment visitPayment = new VisitPayment();
		visitPayment.setPatientVisit(patientVisit);

		model.addAttribute("visitPayment", visitPayment);

		return "visitPayment/addVisitPayment";
	}

}
