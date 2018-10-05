package com.scrum.challenge.service;


import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scrum.challenge.dao.HeroDAO;
import com.scrum.challenge.model.Hero;

@Service
public class HeroService implements UserDetailsService {
	
	@Autowired
	@Qualifier("mongoHeroDAO")
	private HeroDAO heroDAO;
	
	public void save(Hero hero) {
		String password = DigestUtils.md5Hex(hero.getPassword());
		hero.setPassword(password);
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails hero = heroDAO.findByName(username);
		if (hero == null) {
			throw new UsernameNotFoundException(username + " NÃO FOI ENCONTRADO NA BASE DE DADOS.");
		}
		return hero;
	}
	
}
