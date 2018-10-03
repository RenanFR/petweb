package com.scrum.challenge.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.scrum.challenge.dao.HeroDAO;
import com.scrum.challenge.model.Hero;

@Service
public class HeroService {
	
	@Autowired
	@Qualifier("mongoHeroDAO")
	private HeroDAO heroDAO;
	
	public void save(Hero hero) {
		heroDAO.save(hero);
	}
	
	public List<Hero> findAll() {
		return heroDAO.findAll();
	}
	
	public Hero findById(ObjectId id) {
		return heroDAO.findById(id);
	}
	
	public void delete(Hero hero) {
		heroDAO.delete(hero);
	}
	
}
