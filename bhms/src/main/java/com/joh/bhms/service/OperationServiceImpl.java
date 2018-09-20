package com.joh.bhms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.bhms.dao.OperationDAO;
import com.joh.bhms.model.Operation;

@Service
public class OperationServiceImpl implements OperationService {
	@Autowired
	private OperationDAO operationDAO;

	@Override
	public Iterable<Operation> findAll() {
		return operationDAO.findAll();
	}
}
