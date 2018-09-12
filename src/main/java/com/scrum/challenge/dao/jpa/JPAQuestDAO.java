package com.scrum.challenge.dao.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scrum.challenge.dao.QuestDAO;
import com.scrum.challenge.model.Quest;

@Repository("jpaQuestDAO")
public interface JPAQuestDAO extends CrudRepository<Quest, Long>, QuestDAO	{
}
