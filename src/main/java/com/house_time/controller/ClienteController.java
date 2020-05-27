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

import com.house_time.models.Usuario;
import com.house_time.models.Endereco;
import com.house_time.repositorios.UsuarioRepositorio;
import com.house_time.repositorios.EnderecoRepositorio;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private UsuarioRepositorio usuarios;

	@Autowired
	private EnderecoRepositorio enderecos;

	@GetMapping
	public ModelAndView listar() {

		ModelAndView modelAndView = new ModelAndView("cliente/sucesso");

		modelAndView.addObject("usuarios", usuarios.findAll());

		return modelAndView;
	}

	@GetMapping("/novo")
	public ModelAndView novo(Usuario usuario) {

		ModelAndView modelAndView = new ModelAndView("cliente/cadastrousuario1");

		modelAndView.addObject(usuario);

		return modelAndView;
	}

	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {

		Endereco e = usuario.getEndereco();
		if (result.hasErrors()) {

			return novo(usuario);
		}

		enderecos.save(e);

		usuario.setEndereco(e);
		usuarios.save(usuario);
		
		
		

		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!!");
		return new ModelAndView("redirect:/cliente");

	}
	
}
