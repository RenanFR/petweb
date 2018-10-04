package com.scrum.challenge.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scrum.challenge.model.Hero;
import com.scrum.challenge.model.Quest;
import com.scrum.challenge.service.HeroService;
import com.scrum.challenge.service.QuestService;

@Controller
@RequestMapping("quest")
public class QuestController {
	
	@Autowired
	private QuestService questService;
	
	@Autowired
	private HeroService heroService;

	@GetMapping("form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("quest/form");
		addHeroesList(modelAndView);
		modelAndView.addObject("quest", new Quest());
		return modelAndView;
	}

	private void addHeroesList(ModelAndView modelAndView) {
		List<Hero> listHeroes = heroService.findAll();
		Map<String, Hero> mapHeroes = new HashMap<>();
		listHeroes
			.forEach(h -> mapHeroes.put(h.getObjectId().toString(), h));
		modelAndView.addObject("listHeroes", mapHeroes);
	}
	
	@PostMapping
	public ModelAndView save(@ModelAttribute("quest") Quest quest,
			BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("redirect:/quest/");
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
		addHeroesList(modelAndView);
		modelAndView.addObject("quest", questService.findById(id));
		return modelAndView;
	}
	
	@GetMapping(value = "{id}")
	public ModelAndView detailsQuest(@PathVariable("id")ObjectId id) {
		ModelAndView modelAndView = new ModelAndView("quest/details");
		modelAndView.addObject("quest", questService.findById(id));
		return modelAndView;
	}
}
