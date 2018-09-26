package com.scrum.challenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scrum.challenge.model.Hero;

@Controller
@RequestMapping("hero")
public class HeroController {
	
	@GetMapping("form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("hero/form");
		modelAndView.addObject("hero", new Hero());
		return modelAndView;
	}	

}
