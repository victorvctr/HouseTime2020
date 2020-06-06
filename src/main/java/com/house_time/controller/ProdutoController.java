package com.house_time.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {

		return novo(produtos.getOne(id));
	}
	
	@PostMapping(value = "excluir/{id}")
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {

		produtos.deleteById(id);

		attributes.addFlashAttribute("mensagem", "produto excluido com sucesso!!");

		return "redirect:/produto";
	}
	
	
	
	@PostMapping("/salvar")
	public ModelAndView Cadastrar(@Valid Produto produto, BindingResult result, RedirectAttributes attributes) {

		
		if (result.hasErrors()) { 
			System.out.println(result.getErrorCount());
			System.out.println(result.getAllErrors());
			return novo(produto); }
		

		produtos.save(produto);

		attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!!");
		return new ModelAndView("redirect:/produto");
		

	}
	

	
	@GetMapping("/apple")
	public String apple() {
		return "produto/apple";
	}
	
	@GetMapping("/samsung")
	public String sansung() {
		return "produto/samsung";
	}
	@GetMapping("/diesel")
	public String diesel() {
		return "produto/diesel";
	}
	@GetMapping("/fossil")
	public String fossil() {
		return "produto/fossil";
	}
	@GetMapping("/montblanc")
	public String montblanc() {
		return "produto/montblanc";
	}
	@GetMapping("/xiaomi")
	public String xiaomi() {
		return "produto/xiaomi";
	}
}
