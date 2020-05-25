package com.house_time.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class HomeController {
	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/sobre")
	public ModelAndView sobre() {
		ModelAndView mv = new ModelAndView("/sobre");
		return mv;

	}
	

	@RequestMapping(method = RequestMethod.GET, value = "/contato")
	public ModelAndView contato() {
		ModelAndView mv = new ModelAndView("/contato");
		return mv;

	}

}
