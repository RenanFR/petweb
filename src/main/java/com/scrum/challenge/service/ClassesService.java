package com.scrum.challenge.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.scrum.challenge.dao.ClassesDAO;
import com.scrum.challenge.model.Classes;

@Service
public class ClassesService {
	
	@Autowired
	@Qualifier("mongoClassesDAO")
	private ClassesDAO classesDAO;
	
	public void save(Classes classes) {
		classesDAO.save(classes);
	}
	
	public List<Classes> findAll() {
		return classesDAO.findAll();
	}
	
	public Classes findById(ObjectId id) {
		return classesDAO.findById(id);
	}
	
	public void delete(Classes classes) {
		classesDAO.delete(classes);
	}	

}
