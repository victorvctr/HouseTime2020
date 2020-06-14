package com.house_time.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.house_time.models.Contato;

import com.house_time.repositorios.ContatoRepositorio;

@Controller
@RequestMapping("/contato")
public class ContatoController {

	@Autowired
	private ContatoRepositorio contatos;
	

@GetMapping("/novo")
public ModelAndView novo(Contato contato) {

	ModelAndView modelAndView = new ModelAndView("contato");

	modelAndView.addObject("contatos", contatos.findAll());

	modelAndView.addObject(contatos);

	return modelAndView;
   }
}