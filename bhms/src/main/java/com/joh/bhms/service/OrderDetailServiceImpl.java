package com.joh.bhms.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.joh.bhms.dao.OrderDetailDAO;
import com.joh.bhms.exception.CusDataIntegrityViolationException;
import com.joh.bhms.model.OrderDetail;
import com.joh.bhms.model.Product;


@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailDAO orderDetailDAO;

	@Autowired
	private ProductService productService;

	@Transactional
	@Override
	public OrderDetail save(OrderDetail orderDetail) {
		Product checkProduct = productService.findByCode(orderDetail.getProduct().getCode());
		if (checkProduct == null) {
			Product product = productService.save(orderDetail.getProduct());
			orderDetail.setProduct(product);
		} else {
			orderDetail.setProduct(checkProduct);
		}

		if (orderDetail.getSoldAmount() == null)
			orderDetail.setSoldAmount(0);

		return orderDetailDAO.save(orderDetail);
	}

	@Transactional
	@Override
	public void delete(OrderDetail orderDetail) {
		try {
			orderDetailDAO.delete(orderDetail.getId());
		} catch (DataIntegrityViolationException e) {
			throw new CusDataIntegrityViolationException(
					"can not remove this OrderDetail  code=" + orderDetail.getProduct().getCode());
		}
	}

	@Override
	public List<OrderDetail> findAllByOrderTime(Date from, Date to) {
		return orderDetailDAO.findAllByOrderOrderTimeBetween(from, to);
	}

	@Override
	public OrderDetail findFirstByProductCode(String code) {
		return orderDetailDAO.findByProductCode(code);
	}

}
