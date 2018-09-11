package com.scrum.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	
}
