package com.house_time.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

@PostMapping("/enviar")
public ModelAndView enviar(@Valid Contato contato, BindingResult result, RedirectAttributes attributes) {
	contato.setContato(contato.findByNome("Contato"));
	return null;

}
}
