package com.scrum.challenge.model.converter;

import org.bson.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SkillsConverter implements Converter<Document, String>{

	@Override
	public String convert(Document document) {
		return document.toJson();
	}
}
