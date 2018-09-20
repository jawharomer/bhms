package com.joh.bhms.dao;



import org.springframework.data.repository.CrudRepository;

import com.joh.bhms.model.Operation;

public interface OperationDAO extends CrudRepository<Operation, Integer> {

}
