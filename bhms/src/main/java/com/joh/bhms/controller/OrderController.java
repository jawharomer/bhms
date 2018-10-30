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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joh.bhms.exception.CusDataIntegrityViolationException;
import com.joh.bhms.model.Order;
import com.joh.bhms.model.ProductCategory;
import com.joh.bhms.model.Vendor;
import com.joh.bhms.service.OrderService;
import com.joh.bhms.service.ProductCategoryService;
import com.joh.bhms.service.VendorService;

@Controller()
@RequestMapping(path = "/orders")
public class OrderController {

	private static final Logger logger = Logger.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@Autowired
	private VendorService vendorService;

	@Autowired
	private ProductCategoryService productCategoryService;

	@GetMapping()
	public String getAllOrder(@RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getAllOrder->fired");
		logger.info("from=" + from);
		logger.info("to=" + to);

		List<Order> orders = orderService.findAllByOrderTimeBetween(from, to);

		logger.info("orders=" + orders);

		model.addAttribute("orders", orders);
		model.addAttribute("from", from);
		model.addAttribute("to", to);

		return "getAllOrder";

	}

	@GetMapping(path = "/{id}")
	public String getOrder(@PathVariable int id, Model model) {
		logger.info("getOrder->fired");
		logger.info("orderId=" + id);

		Order order = orderService.findOne(id);

		logger.info("order=" + order);

		model.addAttribute("order", order);

		return "getOrder";
	}

	@GetMapping(path = "/add")
	public String getAddingOrder(Model model) throws JsonProcessingException {
		logger.info("getAddingOrder->fired");
		ObjectMapper mapper = new ObjectMapper();

		Iterable<Vendor> vendors = vendorService.findAll();

		logger.info("vendors=" + vendors);

		model.addAttribute("jsonVendors", mapper.writeValueAsString(vendors));

		Iterable<ProductCategory> productCategories = productCategoryService.findAll();

		logger.info("productCategories=" + productCategories);

		model.addAttribute("jsonProductCategories", mapper.writeValueAsString(productCategories));

		Order order = new Order();
		logger.info("order=" + order);

		model.addAttribute("jsonOrder", mapper.writeValueAsString(order));

		return "addOrder";
	}

	@PostMapping(path = "/add")
	public String addOrder(@RequestBody @Valid Order order, BindingResult result, Model model)
			throws JsonProcessingException {
		logger.info("addOrder->fired");

		logger.info("order=" + order);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			throw new CusDataIntegrityViolationException("Please fill data properly");
		} else {
			orderService.save(order);
			return "success";
		}

	}

	@GetMapping(path = "/edit/{id}")
	public String getEditingOrder(@PathVariable int id, Model model) throws JsonProcessingException {
		logger.info("getEditingOrder->fired");
		logger.info("orderId=" + id);

		ObjectMapper mapper = new ObjectMapper();

		Iterable<Vendor> vendors = vendorService.findAll();

		logger.info("vendors=" + vendors);

		model.addAttribute("jsonVendors", mapper.writeValueAsString(vendors));

		Iterable<ProductCategory> productCategories = productCategoryService.findAll();

		logger.info("productCategories=" + productCategories);

		model.addAttribute("jsonProductCategories", mapper.writeValueAsString(productCategories));

		Order order = orderService.findOne(id);
		logger.info("order=" + order);

		model.addAttribute("jsonOrder", mapper.writeValueAsString(order));

		return "editOrder";
	}

	@PostMapping(path = "/update")
	public String updateOrder(@RequestBody @Valid Order order, BindingResult result, Model model)
			throws JsonProcessingException {
		logger.info("updateOrder->fired");

		logger.info("order=" + order);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			throw new CusDataIntegrityViolationException("Please fill data properly");
		} else {
			orderService.update(order);
			return "success";
		}

	}

	@PostMapping(path = "/delete/{id}")
	public String deleteOrder(@PathVariable int id) throws JsonProcessingException {
		logger.info("deleteOrder->fired");
		logger.info("orderId=" + id);
		orderService.delete(id);
		return "success";

	}

}
