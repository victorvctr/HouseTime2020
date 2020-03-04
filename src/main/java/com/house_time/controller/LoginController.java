package com.house_time.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	@RequestMapping(method=RequestMethod.GET,value ="/login")
	public ModelAndView logar() {
		ModelAndView mv = new ModelAndView("login/login");
		return mv;
	}

}
