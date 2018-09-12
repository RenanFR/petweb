package com.scrum.challenge.service;

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
	
}
