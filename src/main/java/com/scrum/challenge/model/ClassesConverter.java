package com.scrum.challenge.model;

import org.springframework.core.convert.converter.Converter;

public class ClassesConverter implements Converter<String, Classes>{

	@Override
	public Classes convert(String source) {
		return new Classes(source);
	}

}
