package com.joh.bhms.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joh.bhms.model.Vendor;
import com.joh.bhms.service.VendorService;
import com.joh.bhms.validator.VendorValidation;

@Controller()
@RequestMapping(path = "/vendors")
public class VendorController {

	private static final Logger logger = Logger.getLogger(VendorController.class);

	@Autowired
	private VendorService vendorService;

	@GetMapping()
	public String getAllVendor(Model model) {
		logger.info("getAllVendor->fired");
		model.addAttribute("vendors", vendorService.findAll());

		return "vendors";
	}

	@GetMapping(path = "/add")
	public String getAddingVendor(Model model) {
		logger.info("getAddingVendor->fired");

		model.addAttribute("vendor", new Vendor());

		return "vendor/addVendor";
	}

	@PostMapping(path = "/add")
	public String addVendor(@RequestBody @Valid Vendor vendor, BindingResult result, Model model) {
		logger.info("addVendor->fired");

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {

			model.addAttribute("vendor", vendor);
			return "vendor/addVendor";
		} else {
			vendorService.save(vendor);
			return "success";
		}

	}

	@GetMapping(path = "/edit/{id}")
	public String getEditingVendor(@PathVariable int id, Model model) {
		logger.info("getEditingVendor->fired");
		logger.info("vendorId=" + id);

		Vendor vendor = vendorService.findOne(id);
		logger.info("vendor=" + vendor);

		model.addAttribute("vendor", vendor);

		return "vendor/editVendor";
	}

	@PostMapping(path = "/edit")
	public String editVendor(@RequestBody @Validated(VendorValidation.Insert.class) Vendor vendor, BindingResult result,
			Model model) {
		logger.info("editVendor->fired");

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {

			model.addAttribute("vendor", vendor);
			return "vendor/editVendor";
		} else {
			vendorService.save(vendor);
			return "success";
		}

	}

	@PostMapping(path = "/delete/{id}")
	public String deleteVendor(@PathVariable int id) {
		logger.info("deleteVendor->fired");
		logger.info("vendorId=" + id);
		vendorService.delete(id);
		return "success";
	}
}
