package com.house_time.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProdutoController {

	@RequestMapping(method = RequestMethod.GET, value = "/CadastrarProd")
	public ModelAndView inicio() {
		ModelAndView mv = new ModelAndView("produto/Cadastroproduto1");
		return mv;

	}

}