package com.scrum.challenge.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.scrum.challenge.model.Quest;
import com.scrum.challenge.service.QuestService;

@Controller
@RequestMapping("quest")
public class QuestController {
	
	@Autowired
	private QuestService questService;

	@GetMapping("form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("quest/form");
		modelAndView.addObject("quest", new Quest());
		return modelAndView;
	}
	
	@PostMapping
	public ModelAndView save(@ModelAttribute("quest") Quest quest) {
		ModelAndView modelAndView = new ModelAndView("redirect:/quest/");
		ObjectId objectId = new ObjectId();
		quest.setObjectId(objectId);
		questService.save(quest);
		return modelAndView;
	}
	
	@GetMapping
	public ModelAndView quests() {
		ModelAndView modelAndView = new ModelAndView("quest/list");
		modelAndView.addObject("quests", questService.findAll());
		return modelAndView;
	}
	
	@DeleteMapping
	public ModelAndView deleteQuest(@RequestParam("id")Long id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/quest/");
		return modelAndView;
	}
	
	
}
