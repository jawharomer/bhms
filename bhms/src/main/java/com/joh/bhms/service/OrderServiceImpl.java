package com.joh.bhms.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.bhms.dao.OrderDAO;
import com.joh.bhms.model.Order;
import com.joh.bhms.model.OrderDetail;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private OrderDetailService orderDetailService;

	@Override
	@Transactional
	public Order save(Order order) {

		Order savedOrder = orderDAO.save(order);
		order.getOrderDetails().stream().forEach(e -> {
			e.setOrder(savedOrder);
			orderDetailService.save(e);
		});

		return savedOrder;

	}

	@Override
	public List<Order> findAllByOrderTimeBetween(Date from, Date to) {
		return orderDAO.findAllByOrderTimeBetween(from, to);
	}

	@Override
	public Order findOne(int id) {
		return orderDAO.findOne(id);
	}

	@Transactional
	@Override
	public Order update(Order order) {

		List<OrderDetail> oldOrderDetails = orderDAO.findOne(order.getId()).getOrderDetails();

		for (OrderDetail orderDetail : oldOrderDetails) {

			boolean isExists = order.getOrderDetails().stream().anyMatch(e -> e.getId() == orderDetail.getId());

			if (!isExists) {
				orderDetailService.delete(orderDetail);
			}

		}

		order.getOrderDetails().stream().filter(e -> e.getId() == null).forEach(e -> {
			e.setOrder(order);
			orderDetailService.save(e);
		});

		return orderDAO.save(order);
	}

	@Transactional
	@Override
	public void delete(int id) {
		Order order = orderDAO.findOne(id);
		order.getOrderDetails().stream().forEach(e -> {
			orderDetailService.delete(e);
		});
		orderDAO.delete(id);
	}
}
