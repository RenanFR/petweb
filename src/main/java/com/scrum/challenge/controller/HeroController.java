package com.scrum.challenge.controller;

import java.util.Arrays;
import java.util.HashMap;
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
import com.scrum.challenge.model.Skills;
import com.scrum.challenge.service.HeroService;

@Controller
@RequestMapping("hero")
public class HeroController {
	
	@Autowired
	private HeroService heroService;
	
	@GetMapping("form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("hero/form");
		Map<String, String> skills = new HashMap<>();
		for (Skills sk : Arrays.asList(Skills.values())) {
			skills.put(sk.name(), sk.getDescription());
		}
		modelAndView.addObject("skills", skills);
		modelAndView.addObject("hero", new Hero());
		return modelAndView;
	}
	
	@GetMapping
	public ModelAndView heroes() {
		ModelAndView modelAndView = new ModelAndView("hero/list");
		modelAndView.addObject("heroes", heroService.findAll());
		return modelAndView;
	}
	
	@PostMapping
	public ModelAndView save(@ModelAttribute("hero") Hero hero, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("redirect:/hero/");
		heroService.save(hero);
		return modelAndView;
	}
	
	@GetMapping(value = "delete/{id}")
	public ModelAndView deleteHero(@PathVariable("id")ObjectId id, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("redirect:/hero/");
		Hero hero = heroService.findById(id);
		heroService.delete(hero);
		response.setHeader("Location", "/quest/");
		response.setStatus(303);
		return modelAndView;
	}
	
	@GetMapping(value = "edit/{id}")
	public ModelAndView editHero(@PathVariable("id")ObjectId id) {
		ModelAndView modelAndView = new ModelAndView("hero/form");
		modelAndView.addObject("hero", heroService.findById(id));
		return modelAndView;
	}
	
	@GetMapping(value = "{id}")
	public ModelAndView detailsQuest(@PathVariable("id")ObjectId id) {
		ModelAndView modelAndView = new ModelAndView("hero/details");
		modelAndView.addObject("hero", heroService.findById(id));
		return modelAndView;
	}	

}
