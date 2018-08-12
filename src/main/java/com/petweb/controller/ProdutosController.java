package com.petweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	
	@GetMapping
	public ModelAndView index(@RequestParam("nome")String nome, Model model) {
		ModelAndView view = new ModelAndView("index");
		view.addObject("nome", nome);
		return view;
	}

}
