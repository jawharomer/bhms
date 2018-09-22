package com.joh.bhms.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joh.bhms.domain.model.JsonResponse;
import com.joh.bhms.exception.CusDataIntegrityViolationException;
import com.joh.bhms.model.Doctor;
import com.joh.bhms.model.Operation;
import com.joh.bhms.model.Patient;
import com.joh.bhms.model.PatientVisit;
import com.joh.bhms.service.DoctorService;
import com.joh.bhms.service.OperationService;
import com.joh.bhms.service.PatienService;
import com.joh.bhms.service.PatientVisitService;

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

	@Autowired
	private PatientVisitService patientVisitService;

	@GetMapping()
	public String getAllPatientVisit(Model model) {
		logger.info("getAllPatientVisit->fired");

		Iterable<PatientVisit> patientVisits = patientVisitService.findAll();

		logger.info("patientVisits=" + patientVisits);

		model.addAttribute("patientVisits", patientVisits);

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

	@PostMapping(path = "/add")
	@ResponseBody
	public JsonResponse addPatientVisit(@RequestBody @Valid PatientVisit patientVisit, BindingResult result) {
		logger.info("addPatientVisit->fired");
		logger.info("patientVisit=" + patientVisit);

		logger.info("erorrs=" + result.getAllErrors());

		if (result.hasErrors()) {
			throw new CusDataIntegrityViolationException("bad input is entered");
		} else {
			patientVisit = patientVisitService.save(patientVisit);

			JsonResponse jsonResponse = new JsonResponse();
			jsonResponse.setStatus(200);
			jsonResponse.setMessage("success");
			jsonResponse.setEtc("" + patientVisit.getId());

			return jsonResponse;
		}

	}
}
