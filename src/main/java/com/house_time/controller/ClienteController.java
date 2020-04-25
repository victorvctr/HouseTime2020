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

import com.house_time.models.Cliente;
import com.house_time.models.Endereco;
import com.house_time.repositorios.ClienteRepositorio;
import com.house_time.repositorios.EnderecoRepositorio;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteRepositorio clientes;

	@Autowired
	private EnderecoRepositorio enderecos;

	@GetMapping
	public ModelAndView listar() {

		ModelAndView modelAndView = new ModelAndView("cliente/sucesso");

		modelAndView.addObject("clientes", clientes.findAll());

		return modelAndView;
	}

	@GetMapping("/novo")
	public ModelAndView novo(Cliente cliente) {

		ModelAndView modelAndView = new ModelAndView("cliente/cadastrousuario1");

		modelAndView.addObject(cliente);

		return modelAndView;
	}

	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {

		Endereco e = cliente.getEndereco();
		if (result.hasErrors()) {

			return novo(cliente);
		}

		enderecos.save(e);

		cliente.setEndereco(e);
		clientes.save(cliente);

		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!!");
		return new ModelAndView("redirect:/cliente");

	}
}
