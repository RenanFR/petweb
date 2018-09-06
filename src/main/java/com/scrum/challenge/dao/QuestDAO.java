package com.scrum.challenge.dao;

import org.springframework.data.repository.CrudRepository;

import com.scrum.challenge.model.Quest;

public interface QuestDAO extends CrudRepository<Quest, Long>{

}
