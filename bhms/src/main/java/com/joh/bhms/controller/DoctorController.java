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

import com.joh.bhms.model.Doctor;
import com.joh.bhms.model.Operation;
import com.joh.bhms.service.DoctorService;
import com.joh.bhms.service.OperationService;

@Controller
@RequestMapping(path = "/doctors")
public class DoctorController {

	private static final Logger logger = Logger.getLogger(DoctorController.class);

	@Autowired
	private DoctorService doctorService;

	@GetMapping()
	public String getAllDoctors(Model model) {
		logger.info("getAllDoctors->fired");

		Iterable<Doctor> doctors = doctorService.findAll();

		logger.info("doctors=" + doctors);

		model.addAttribute("doctors", doctors);

		return "doctors";
	}

	@GetMapping(path = "/add")
	public String getAddingDoctor(Model model) {
		logger.info("getAddingDoctor->fired");

		model.addAttribute("doctor", new Doctor());
		return "doctor/addDoctor";

	}

	@PostMapping(path = "/add")
	public String addOperation(@RequestBody @Valid Doctor doctor, BindingResult result, Model model) {
		logger.info("addOperation->fired");

		logger.info("doctor=" + doctor);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			model.addAttribute("doctor", doctor);
			return "doctor/addDoctor";
		} else {
			doctorService.save(doctor);
			return "success";
		}

	}

	@GetMapping(path = "/edit/{id}")
	public String getEditingDoctor(@PathVariable int id, Model model) {
		logger.info("getEditingDoctor->fired");
		logger.info("id=" + id);

		Doctor doctor = doctorService.findOne(id);

		model.addAttribute("doctor", doctor);
		return "doctor/editDoctor";

	}

	@PostMapping(path = "/update")
	public String updateDoctor(@RequestBody @Valid Doctor doctor, BindingResult result, Model model) {
		logger.info("updateDoctor->fired");

		logger.info("doctor=" + doctor);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			model.addAttribute("doctor", doctor);
			return "doctor/editDoctor";
		} else {
			doctorService.update(doctor);
			return "success";
		}

	}

	@PostMapping(path = "/delete/{id}")
	public String deleteDoctor(@PathVariable int id) {
		logger.info("deleteDoctor->fired");
		logger.info("id=" + id);
		doctorService.delete(id);
		return "success";
	}

}
