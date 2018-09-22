package com.joh.bhms.service;

import javax.persistence.EntityNotFoundException;

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
	
	@Override
	public Operation findOne(int id) {
		return operationDAO.findOne(id);
	}

	@Override
	public Operation save(Operation operation) {
		return operationDAO.save(operation);
	}

	@Override
	public Operation update(Operation operation) {
		if (operationDAO.findOne(operation.getId()) == null)
			throw new EntityNotFoundException();
		return operationDAO.save(operation);
	}
	@Override
	public void delete(int id) {
		operationDAO.delete(id);
	}
}
