package com.scrum.challenge.dao.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scrum.challenge.dao.HeroDAO;
import com.scrum.challenge.model.Hero;

@Repository("jpaHeroDAO")
public interface JPAHeroDAO extends CrudRepository<Hero, Long>, HeroDAO	{
}
