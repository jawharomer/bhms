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

import com.joh.bhms.model.Doctor;
import com.joh.bhms.model.PatientVisit;
import com.joh.bhms.model.VisitPayment;
import com.joh.bhms.service.DoctorService;
import com.joh.bhms.service.PatientVisitService;
import com.joh.bhms.service.VisitPaymentService;

@Controller
@RequestMapping(path = "/visitPayments")
public class VisitPaymentController {

	private static final Logger logger = Logger.getLogger(VisitPaymentController.class);

	@Autowired
	private VisitPaymentService visitPaymentService;

	@Autowired
	private PatientVisitService patientVisitService;

	@Autowired
	private DoctorService doctorService;

	@GetMapping(path = "/patientVisit/{id}")
	public String getAllVisitPatientPayment(@PathVariable int id, Model model) {
		logger.info("getAllVisitPatientPayment->fired");

		logger.info("patientVisitId=" + id);

		List<VisitPayment> visitPayments = visitPaymentService.findAllByPatientVisitId(id);

		model.addAttribute("patientVisit", patientVisitService.findOne(id));
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

	@PostMapping(path = "/add")
	public String addVisitPayment(@RequestBody @Valid VisitPayment visitPayment, BindingResult result, Model model) {
		logger.info("addVisitPayment->fired");
		logger.info("visitPayment=" + visitPayment);
		logger.info("visitPayment.patientVisit=" + visitPayment.getPatientVisit());

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			model.addAttribute("visitPayment", visitPayment);
			return "visitPayment/addVisitPayment";
		} else {
			visitPaymentService.save(visitPayment);
			return "success";
		}

	}

	@PostMapping(path = "/delete/{id}")
	public String deleteVisitPayment(@PathVariable int id, Model model) {
		logger.info("deleteVisitPayment->fired");
		logger.info("id=" + id);

		visitPaymentService.delete(id);

		return "success";

	}

	@GetMapping(path = "/doctors/{id}")
	public String getAllDoctorVisitPayment(@PathVariable int id,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getAllDoctorVisitPayment->fired");

		logger.info("doctorId=" + id);

		logger.info("from=" + from);
		logger.info("to=" + to);

		Doctor doctor = doctorService.findOne(id);
		logger.info("doctor=" + doctor);

		List<VisitPayment> visitPayments = visitPaymentService.findAllByDoctorId(id);

		model.addAttribute("visitPayments", visitPayments);

		model.addAttribute("from", from);
		model.addAttribute("to", to);
		model.addAttribute("doctor", doctor);

		return "doctorVisitPayments";
	}

}
