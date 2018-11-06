package com.joh.bhms.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joh.bhms.domain.model.JsonResponse;
import com.joh.bhms.exception.CusDataIntegrityViolationException;
import com.joh.bhms.model.Doctor;
import com.joh.bhms.model.Operation;
import com.joh.bhms.model.Patient;
import com.joh.bhms.model.PatientProductUsed;
import com.joh.bhms.model.PatientVisit;
import com.joh.bhms.model.Product;
import com.joh.bhms.service.DoctorService;
import com.joh.bhms.service.OperationService;
import com.joh.bhms.service.PatientService;
import com.joh.bhms.service.PatientVisitService;
import com.joh.bhms.service.ProductService;
import com.joh.bhms.service.ReportService;

@Controller
@RequestMapping(path = "/patientVisits")
public class PatientVisitController {

	private static final Logger logger = Logger.getLogger(PatientVisitController.class);

	@Autowired
	private PatientService patienService;

	@Autowired
	private OperationService operationService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PatientVisitService patientVisitService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ReportService reportService;

	@GetMapping()
	public String getAllPatientVisit(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getAllPatientVisit->fired");

		logger.info("from=" + from);
		logger.info("to=" + to);

		Iterable<PatientVisit> patientVisits = patientVisitService.findAllByTimeBetween(from, to);

		logger.info("patientVisits=" + patientVisits);

		model.addAttribute("patientVisits", patientVisits);
		model.addAttribute("from", from);
		model.addAttribute("to", to);

		return "patientVisits";
	}

	@GetMapping(path = "/nextSession")
	public String getAllPatientVisitByNextSession(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
			Model model) {
		logger.info("getAllPatientVisitByNextSession->fired");
		logger.info("to=" + to);

		Iterable<PatientVisit> patientVisits = patientVisitService.findAllByNextSessionLessThanEqual(to);

		logger.info("patientVisits=" + patientVisits);

		model.addAttribute("patientVisits", patientVisits);
		model.addAttribute("to", to);

		return "patientNextSessions";
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

	@GetMapping(path = "/edit/{id}")
	public String getEditingPatientVisit(@PathVariable int id, Model model) throws JsonProcessingException {
		logger.info("getEditingPatientVisit->fired");
		logger.info("patientVisitId=" + id);

		ObjectMapper mapper = new ObjectMapper();

		PatientVisit patientVisit = patientVisitService.findOne(id);

		logger.info("patientVisit=" + patientVisit);

		Iterable<Operation> operations = operationService.findAll();

		Iterable<Doctor> doctors = doctorService.findAll();

		model.addAttribute("jsonOperations", mapper.writeValueAsString(operations));

		model.addAttribute("jsonPatientVisit", mapper.writeValueAsString(patientVisit));
		model.addAttribute("jsonDoctors", mapper.writeValueAsString(doctors));

		Iterable<Product> products = productService.findAll();
		logger.info("products=" + products);
		model.addAttribute("jsonProducts", mapper.writeValueAsString(products));

		List<String> examinations = reportService.findAllExamination();
		logger.info("examinations=" + examinations);
		model.addAttribute("jsonExaminations", mapper.writeValueAsString(examinations));

		return "editPatientVisit";
	}

	@PostMapping(path = "/update")
	public String updatePatientVisit(@RequestBody @Valid PatientVisit patientVisit, BindingResult result) {
		logger.info("updatePatientVisit->fired");
		logger.info("patientVisit=" + patientVisit);

		logger.info("erorrs=" + result.getAllErrors());

		if (result.hasErrors()) {
			throw new CusDataIntegrityViolationException("bad input is entered");
		} else {

			patientVisit = patientVisitService.update(patientVisit);

			return "success";
		}

	}

	@PostMapping(path = "/{id}/attachedFile")
	public String addAttachedFile(@PathVariable int id, @RequestParam MultipartFile file) throws IOException {
		logger.info("addAttachedFile->fired");
		logger.info("patientVisitId=" + id);
		logger.info("FileName=" + file.getOriginalFilename());
		if (!file.isEmpty()) {

			patientVisitService.addAttachedFile(id, file);

			return "success";
		} else {
			throw new CusDataIntegrityViolationException("file is empty");
		}

	}

	@PostMapping(path = "/{id}/attachedFiles/delete/{attachedFileId}")
	public String deleteAttachedFile(@PathVariable int id, @PathVariable int attachedFileId) {
		logger.info("deleteAttachedFile->fired");
		logger.info("id=" + id);
		logger.info("attachedFileId=" + attachedFileId);

		patientVisitService.deleteAttachedFile(id, attachedFileId);

		return "success";

	}

	@GetMapping(path = "/patientProductUseds")
	public String getAllPatietnProductUsed(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getAllPatietnProductUsed->fired");

		logger.info("from=" + from);
		logger.info("to=" + to);

		Iterable<PatientVisit> patientVisits = patientVisitService.findAllByTimeBetween(from, to);

		logger.info("patientVisits=" + patientVisits);

		model.addAttribute("patientVisits", patientVisits);
		model.addAttribute("from", from);
		model.addAttribute("to", to);

		return "patientProductUseds";
	}

	@PostMapping(path = "/{id}/patientProductUsed/add")
	public String addPatientProductUsed(@PathVariable int id, @RequestBody PatientProductUsed patientProductUsed) {
		logger.info("patientProductUsed->fired");
		logger.info("id=" + id);
		logger.info("PatientProductUsed=" + patientProductUsed);

		patientVisitService.addPatientProductUsed(id, patientProductUsed);

		return "success";
	}

	@PostMapping(path = "/{id}/patientProductUsed/delete/{patientProductUsedId}")
	public String deletePatientProductUsed(@PathVariable int id, @PathVariable int patientProductUsedId) {
		logger.info("deletePatientProductUsed->fired");
		logger.info("id=" + id);
		logger.info("patientProductUsedId=" + patientProductUsedId);

		patientVisitService.deletePatientProductUsed(id, patientProductUsedId);

		return "success";
	}

	@GetMapping(path = "/patient/{id}")
	public String getAllPatientPatientVisits(@PathVariable int id, Model model) {
		logger.info("getAllPatientPatientVisits->fired");
		logger.info("patientId=" + id);

		Iterable<PatientVisit> patientVisits = patientVisitService.findAllByPatientId(id);

		logger.info("patientVisits=" + patientVisits);

		model.addAttribute("patientVisits", patientVisits);

		return "patientPatientVisits";
	}

}
