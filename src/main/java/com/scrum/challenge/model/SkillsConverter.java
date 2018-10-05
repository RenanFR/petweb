package com.scrum.challenge.model;

import org.springframework.core.convert.converter.Converter;

public class SkillsConverter implements Converter<Skills, String>{

	@Override
	public String convert(Skills source) {
		return source.getDescription();
	}

}
