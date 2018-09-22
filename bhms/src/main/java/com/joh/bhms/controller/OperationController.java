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

import com.joh.bhms.model.Operation;
import com.joh.bhms.service.OperationService;

@Controller
@RequestMapping(path = "/operations")
public class OperationController {

	private static final Logger logger = Logger.getLogger(OperationController.class);

	@Autowired
	private OperationService operationService;

	@GetMapping()
	public String getAllOperation(Model model) {
		logger.info("getAllOperation->fired");

		Iterable<Operation> operations = operationService.findAll();

		logger.info("operations=" + operations);

		model.addAttribute("operations", operations);

		return "operations";
	}

	@GetMapping(path = "/add")
	public String getAddingOperation(Model model) {
		logger.info("getAddingOperation->fired");

		model.addAttribute("operation", new Operation());
		return "operation/addOperation";

	}

	@PostMapping(path = "/add")
	public String addOperation(@RequestBody @Valid Operation operation, BindingResult result, Model model) {
		logger.info("addOperation->fired");

		logger.info("operation=" + operation);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			model.addAttribute("operation", operation);
			return "operation/addOperation";
		} else {
			operationService.save(operation);
			return "success";
		}

	}

	@GetMapping(path = "/edit/{id}")
	public String getEditingOperation(@PathVariable int id, Model model) {
		logger.info("getEditingOperation->fired");
		logger.info("id=" + id);

		Operation operation = operationService.findOne(id);

		model.addAttribute("operation", operation);
		return "operation/editOperation";

	}

	@PostMapping(path = "/update")
	public String updateOperation(@RequestBody @Valid Operation operation, BindingResult result, Model model) {
		logger.info("updateOperation->fired");

		logger.info("operation=" + operation);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			model.addAttribute("operation", operation);
			return "operation/editOperation";
		} else {
			operationService.update(operation);
			return "success";
		}

	}

	@PostMapping(path = "/delete/{id}")
	public String deleteOperation(@PathVariable int id) {
		logger.info("deleteOperation->fired");
		logger.info("id=" + id);
		operationService.delete(id);
		return "success";
	}

}
