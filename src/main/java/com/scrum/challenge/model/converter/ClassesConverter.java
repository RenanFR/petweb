package com.scrum.challenge.model.converter;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.scrum.challenge.model.Classes;
import com.scrum.challenge.service.ClassesService;

@Component
public class ClassesConverter implements Converter<String, Classes>{
	
	@Autowired
	private ClassesService classesService;

	@Override
	public Classes convert(String source) {
		Classes classes = classesService.findById(new ObjectId(source));
		return classes;
	}

}
