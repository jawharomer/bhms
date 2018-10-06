package com.joh.bhms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.bhms.model.AttachedFile;

public interface AttachedFileDAO extends CrudRepository<AttachedFile, Integer> {

}
