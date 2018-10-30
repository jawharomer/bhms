package com.joh.bhms.service;

import java.util.Date;
import java.util.List;

import com.joh.bhms.model.Order;

public interface OrderService {

	Order save(Order order);

	List<Order> findAllByOrderTimeBetween(Date from, Date to);

	Order findOne(int id);

	void delete(int id);

	Order update(Order order);

}
