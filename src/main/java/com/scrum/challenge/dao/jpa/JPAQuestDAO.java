package com.scrum.challenge.dao.jpa;

import org.springframework.data.repository.CrudRepository;

import com.scrum.challenge.model.Quest;

public interface JPAQuestDAO extends CrudRepository<Quest, Long>{

}
