package com.scrum.challenge.dao;

import java.util.List;

import org.bson.types.ObjectId;

import com.scrum.challenge.model.Classes;

public interface ClassesDAO {

	Classes save(Classes classes);

	List<Classes> findAll();
	
	Classes findById(ObjectId id);
	
	void delete(Classes classes);	
	
}
