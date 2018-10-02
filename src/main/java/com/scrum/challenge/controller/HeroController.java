package com.scrum.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scrum.challenge.model.Hero;
import com.scrum.challenge.service.HeroService;

@Controller
@RequestMapping("hero")
public class HeroController {
	
	@Autowired
	private HeroService heroService;
	
	@GetMapping("form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("hero/form");
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
		ModelAndView modelAndView = new ModelAndView("redirect:/quest/");
		heroService.save(hero);
		return modelAndView;
	}	

}
