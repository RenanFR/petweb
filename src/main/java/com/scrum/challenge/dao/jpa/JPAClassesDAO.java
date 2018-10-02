package com.scrum.challenge.dao.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scrum.challenge.dao.ClassesDAO;
import com.scrum.challenge.model.Classes;

@Repository("jpaClassesDAO")
public interface JPAClassesDAO extends CrudRepository<Classes, Long>, ClassesDAO{
}
