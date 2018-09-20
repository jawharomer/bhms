package com.joh.bhms.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joh.bhms.model.Doctor;
import com.joh.bhms.model.Operation;
import com.joh.bhms.model.Patient;
import com.joh.bhms.model.PatientVisit;
import com.joh.bhms.service.DoctorService;
import com.joh.bhms.service.OperationService;
import com.joh.bhms.service.PatienService;

@Controller
@RequestMapping(path = "/patientVisits")
public class PatientVisitController {

	private static final Logger logger = Logger.getLogger(PatientVisitController.class);

	@Autowired
	private PatienService patienService;

	@Autowired
	private OperationService operationService;

	@Autowired
	private DoctorService doctorService;

	@GetMapping()
	public String getAllPatientVisit() {
		logger.info("getAllPatientVisit->fired");
		return "patientVisits";
	}

	@GetMapping(path = "/add/{id}")
	public String getAddingPatientVisit(@PathVariable int id, Model model) throws JsonProcessingException {
		logger.info("getAddingPatientVisit->fired");
		logger.info("patienId=" + id);

		ObjectMapper mapper = new ObjectMapper();

		Patient patient = patienService.findOne(id);

		PatientVisit patientVisit = new PatientVisit();
		patientVisit.setPatient(patient);

		Iterable<Operation> operations = operationService.findAll();

		Iterable<Doctor> doctors = doctorService.findAll();

		model.addAttribute("jsonOperations", mapper.writeValueAsString(operations));

		model.addAttribute("jsonPatientVisit", mapper.writeValueAsString(patientVisit));
		model.addAttribute("jsonDoctors", mapper.writeValueAsString(doctors));
		return "addPatientVisit";
	}
}
