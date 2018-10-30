package com.joh.bhms.service;

import java.util.Date;
import java.util.List;

import com.joh.bhms.model.OrderDetail;

public interface OrderDetailService {

	OrderDetail save(OrderDetail orderDetail);

	void delete(OrderDetail orderDetail);

	List<OrderDetail> findAllByOrderTime(Date from, Date to);

	OrderDetail findFirstByProductCode(String code);

}
