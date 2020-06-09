package com.house_time.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.house_time.models.Endereco;
import com.house_time.models.Usuario;
import com.house_time.models.UsuarioSistema;
import com.house_time.repositorios.EnderecoRepositorio;
import com.house_time.repositorios.GrupoRepositorio;
import com.house_time.repositorios.UsuarioRepositorio;
import com.house_time.security.AppUserDetailsService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private UsuarioRepositorio usuarios;

	@Autowired
	private EnderecoRepositorio enderecos;

	@Autowired
	private GrupoRepositorio grupos;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private UsuarioSistema user_cliente;

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

		if (usuarios.existsByEmail(usuario.getEmail()) && !usuarios.existsById(usuario.getId())) {

			result.rejectValue("email", "usuario.email.existente");
		}
		if (usuarios.existsByCpf(usuario.getCpf()) && !usuarios.existsById(usuario.getId())) {

			result.rejectValue("cpf", "usuario.cpf.existente");
		}

		Endereco e = usuario.getEndereco();
		if (result.hasErrors()) {

			return novo(usuario);
		}

		enderecos.save(e);

		usuario.setEndereco(e);

		usuario.setGrupos(grupos.findByNome("Cliente"));

		String senha = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senha);
		usuario.setAtivo(true);

		usuarios.saveAndFlush(usuario);

		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!!");
		
		return new ModelAndView("redirect:/login");

		//return loginCliente(usuarios.findByEmail(usuario.getEmail()), null);

	}

	public ModelAndView loginCliente(Usuario cliente, @AuthenticationPrincipal User user) {

		AppUserDetailsService appUser = new AppUserDetailsService();
		
		user = (User) appUser.loadUserByUsername("marlenelacerda@uol.com");

		if (user != null) {
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("login");
	}

}
