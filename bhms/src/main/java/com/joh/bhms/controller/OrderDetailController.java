package com.joh.bhms.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joh.bhms.model.OrderDetail;
import com.joh.bhms.service.OrderDetailService;

@Controller()
@RequestMapping(path = "/orderDetails")
public class OrderDetailController {

	@Autowired
	private OrderDetailService orderDetailService;

	private static final Logger logger = Logger.getLogger(OrderDetailController.class);

	@GetMapping()
	public String getAllOrderDetail(@RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getAllOrderDetail->fired");
		logger.info("from=" + from);
		logger.info("to=" + to);

		List<OrderDetail> orderDetails = orderDetailService.findAllByOrderTime(from, to);

		logger.info("orderDetails=" + orderDetails);

		model.addAttribute("orderDetails", orderDetails);
		model.addAttribute("from", from);
		model.addAttribute("to", to);

		return "orderDetails";
	}

}
