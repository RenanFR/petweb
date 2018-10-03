package com.scrum.challenge.controller;

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
	
	@PostMapping
	public ModelAndView save(@ModelAttribute("classes") Classes classes, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("redirect:/classes/");
		classesService.save(classes);
		return modelAndView;
	}
	
	@GetMapping(value = "delete/{id}")
	public ModelAndView deleteHero(@PathVariable("id")ObjectId id, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("redirect:/classes/");
		Classes classes = classesService.findById(id);
		classesService.delete(classes);
		response.setHeader("Location", "/quest/");
		response.setStatus(303);
		return modelAndView;
	}
	
	@GetMapping(value = "edit/{id}")
	public ModelAndView editClass(@PathVariable("id")ObjectId id) {
		ModelAndView modelAndView = new ModelAndView("classes/form");
		modelAndView.addObject("classes", classesService.findById(id));
		return modelAndView;
	}	
	
}
