package com.scrum.challenge.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.scrum.challenge.dao.QuestDAO;
import com.scrum.challenge.model.Quest;

@Service
public class QuestService {
	
	@Autowired
	@Qualifier("mongoQuestDAO")
	private QuestDAO questDAO;
	
	public void save(Quest quest) {
		questDAO.save(quest);
	}
	
	public List<Quest> findAll() {
		return questDAO.findAll();
	}
	
	public Quest findById(ObjectId id) {
		return questDAO.findById(id);
	}
	
	public void delete(Quest quest) {
		questDAO.delete(quest);
	}
	
}
