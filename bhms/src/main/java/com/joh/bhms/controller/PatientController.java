package com.joh.bhms.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joh.bhms.model.Doctor;
import com.joh.bhms.model.Patient;
import com.joh.bhms.model.VisitReference;
import com.joh.bhms.service.DoctorService;
import com.joh.bhms.service.PatientService;
import com.joh.bhms.service.VisitReferenceService;

@Controller()
@RequestMapping(path = "/patients")
public class PatientController {

	private static final Logger logger = Logger.getLogger(PatientController.class);

	@Autowired
	private PatientService patientService;

	@Autowired
	private VisitReferenceService visitReferenceService;

	@Autowired
	private DoctorService doctorService;

	@ModelAttribute
	public void addDoctors(Model model) {
		Iterable<Doctor> doctors = doctorService.findAll();
		model.addAttribute("doctors", doctors);
	}

	@GetMapping()
	public String getAllPatient(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getAllPatient->fired");

		logger.info("from=" + from);
		logger.info("to=" + to);

		Iterable<Patient> patients = patientService.findAll(from, to);

		logger.info("patients=" + patients);

		model.addAttribute("patients", patients);
		model.addAttribute("from", from);
		model.addAttribute("to", to);

		return "patients";
	}

	@GetMapping(path = "/add")
	public String getAddingPatient(Model model) throws JsonProcessingException {
		logger.info("getAddingPatient->fired");

		ObjectMapper mapper = new ObjectMapper();

		Iterable<VisitReference> visitReferences = visitReferenceService.findAll();

		Patient patient = new Patient();

		model.addAttribute("jsonPatient", mapper.writeValueAsString(patient));
		model.addAttribute("jsonVisitReferences", mapper.writeValueAsString(visitReferences));

		return "addPatient";
	}

	@PostMapping(path = "/add")
	public String addPatient(@RequestBody @Valid Patient patient, BindingResult result, HttpServletResponse response,
			Model model) throws JsonProcessingException {
		logger.info("addPatient->fired");

		logger.info("patient=" + patient);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {

			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			ObjectMapper mapper = new ObjectMapper();

			Iterable<VisitReference> visitReferences = visitReferenceService.findAll();

			model.addAttribute("jsonPatient", mapper.writeValueAsString(patient));
			model.addAttribute("jsonVisitReferences", mapper.writeValueAsString(visitReferences));

			return "addPatient";

		} else {
			patientService.save(patient);
			return "success";
		}

	}

	@GetMapping(path = "/edit/{id}")
	public String getEditingPatient(@PathVariable int id, Model model) throws JsonProcessingException {
		logger.info("getEditingPatient->fired");
		logger.info("id=" + id);

		ObjectMapper mapper = new ObjectMapper();

		Iterable<VisitReference> visitReferences = visitReferenceService.findAll();

		Patient patient = patientService.findOne(id);
		logger.info("patient=" + patient);

		model.addAttribute("jsonPatient", mapper.writeValueAsString(patient));
		System.err.println(" mapper.writeValueAsString(patient)=" + mapper.writeValueAsString(patient));

		model.addAttribute("jsonVisitReferences", mapper.writeValueAsString(visitReferences));

		return "editPatient";
	}

	@PostMapping(path = "/delete/{id}")
	private String deletePatient(@PathVariable int id) {
		logger.info("deletePatient->fired");
		logger.info("id=" + id);

		patientService.delete(id);

		return "success";
	}

}
