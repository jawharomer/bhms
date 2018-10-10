package com.joh.bhms.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joh.bhms.model.Patient;
import com.joh.bhms.model.VisitReference;
import com.joh.bhms.service.PatienService;
import com.joh.bhms.service.VisitReferenceService;
import com.joh.bhms.validator.PatientValidation;

@Controller
@RequestMapping(path = "/patients")
public class PatientController {

	private static final Logger logger = Logger.getLogger(PatientController.class);

	@Autowired
	private PatienService patienService;

	@Autowired
	private VisitReferenceService visitReferenceService;

	@GetMapping
	public String getAllPatinet(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getAllPatinet->fired");

		logger.info("from=" + from);
		logger.info("to=" + to);

		List<Patient> patients = patienService.findAllByTimeBetween(from, to);
		model.addAttribute("patients", patients);
		model.addAttribute("from", from);
		model.addAttribute("to", to);

		return "patients";
	}

	@GetMapping(path = "/add")
	public String getAddingPatinet(Model model) {
		logger.info("getAddingPatinet->fired");

		Iterable<VisitReference> visitReferences = visitReferenceService.findAll();

		model.addAttribute("visitReferences", visitReferences);
		model.addAttribute("patient", new Patient());

		return "patient/addPatient";
	}

	@PostMapping(path = "/add")
	public String addPatient(@RequestBody @Validated(PatientValidation.Insert.class) Patient patient,
			BindingResult result, Model model) {
		logger.info("addPatient->fired");

		logger.info("patient=" + patient);

		logger.info("errors=" + result.getAllErrors());
		if (result.hasErrors()) {

			Iterable<VisitReference> visitReferences = visitReferenceService.findAll();

			model.addAttribute("visitReferences", visitReferences);
			model.addAttribute("patient", patient);

			return "patient/addPatient";

		} else {
			patienService.save(patient);
			return "success";
		}
	}

	@GetMapping(path = "/edit/{id}")
	public String getEditingPatient(@PathVariable int id, Model model) {
		logger.info("getEditingPatient->fired");

		logger.info("id=" + id);

		Iterable<VisitReference> visitReferences = visitReferenceService.findAll();

		model.addAttribute("visitReferences", visitReferences);
		Patient patient = patienService.findOne(id);
		logger.info("patient=" + patient);
		model.addAttribute("patient", patient);

		return "patient/editPatient";
	}

	@PostMapping(path = "/update")
	public String updatePatient(@RequestBody @Validated(PatientValidation.Insert.class) Patient patient,
			BindingResult result, Model model) {
		logger.info("updatePatient->fired");

		logger.info("patient=" + patient);

		logger.info("errors=" + result.getAllErrors());
		if (result.hasErrors()) {

			Iterable<VisitReference> visitReferences = visitReferenceService.findAll();

			model.addAttribute("visitReferences", visitReferences);
			model.addAttribute("patient", patient);

			return "patient/editPatient";
		} else {
			patienService.update(patient);
			return "success";
		}
	}

}
