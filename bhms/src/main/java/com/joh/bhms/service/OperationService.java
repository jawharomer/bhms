package com.joh.bhms.service;

import com.joh.bhms.model.Operation;

public interface OperationService {

	Iterable<Operation> findAll();

	Operation save(Operation operation);

	Operation update(Operation operation);

	Operation findOne(int id);

	void delete(int id);

}
