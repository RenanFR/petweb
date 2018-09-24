package com.scrum.challenge.dao;

import java.util.List;

import org.bson.types.ObjectId;

import com.scrum.challenge.model.Quest;

public interface QuestDAO {
	
	Quest save(Quest quest);

	List<Quest> findAll();
	
	Quest findById(ObjectId id);
	
	void delete(Quest quest);	
	
}
