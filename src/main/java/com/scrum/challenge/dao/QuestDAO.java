package com.scrum.challenge.dao;

import java.util.List;

import com.scrum.challenge.model.Quest;

public interface QuestDAO {
	
	Quest save(Quest quest);

	List<Quest> findAll();
	
}
