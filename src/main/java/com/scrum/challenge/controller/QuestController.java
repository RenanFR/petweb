package com.scrum.challenge.controller;

import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping(value = "delete/{id}")
	public ModelAndView deleteQuest(@PathVariable("id")ObjectId id, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("redirect:/quest/");
		Quest quest = questService.findById(id);
		questService.delete(quest);
		response.setHeader("Location", "/quest/");
		response.setStatus(303);
		return modelAndView;
	}
	
	@GetMapping(value = "edit/{id}")
	public ModelAndView editQuest(@PathVariable("id")ObjectId id) {
		ModelAndView modelAndView = new ModelAndView("quest/form");
		modelAndView.addObject("quest", questService.findById(id));
		return modelAndView;
	}
	
}
