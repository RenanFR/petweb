package com.scrum.challenge.model;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.scrum.challenge.service.HeroService;

@Component
public class HeroConverter implements Converter<String, Hero>{
	
	@Autowired
	private HeroService heroService;

	@Override
	public Hero convert(String source) {
		Hero hero = heroService.findById(new ObjectId(source));
		return hero;
	}

}
