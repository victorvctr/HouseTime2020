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


import com.house_time.models.Produto;

import com.house_time.repositorios.ProdutoRepositorio;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepositorio produtos;

	@GetMapping
	public ModelAndView listar() {

		ModelAndView modelAndView = new ModelAndView("produto/lista-produtos");

		modelAndView.addObject("produtos", produtos.findAll());

		return modelAndView;
	}

	@GetMapping("/novo")
	public ModelAndView novo(Produto produto) {

		ModelAndView modelAndView = new ModelAndView("produto/Cadastroproduto1");

		modelAndView.addObject(produto);

		return modelAndView;
	}

	@PostMapping("/salvar")
	public ModelAndView Cadastrar(@Valid Produto produto, BindingResult result, RedirectAttributes attributes) {

		
		if (result.hasErrors()) { 
			System.out.println(result.getErrorCount());
			System.out.println(result.getAllErrors());
			return novo(produto); }
		

		produtos.save(produto);

		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!!");
		return new ModelAndView("redirect:/produto");

	}
}
