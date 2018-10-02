package com.scrum.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scrum.challenge.model.Classes;
import com.scrum.challenge.service.ClassesService;

@Controller
@RequestMapping("classes")
public class ClassesController {
	
	@Autowired
	private ClassesService classesService;
	
	@GetMapping("form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("classes/form");
		modelAndView.addObject("classes", new Classes());
		return modelAndView;
	}
	
	@GetMapping
	public ModelAndView heroes() {
		ModelAndView modelAndView = new ModelAndView("classes/list");
		modelAndView.addObject("classesList", classesService.findAll());
		return modelAndView;
	}	

}
