package com.house_time.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


	@Controller

	public class HomeController {
		@RequestMapping(method=RequestMethod.GET,value ="/iniciar")
		public ModelAndView inicio() {
			ModelAndView mv = new ModelAndView("home/index");
			return mv;
			
		}
	
		@RequestMapping(method=RequestMethod.GET,value ="/sobre")
		public ModelAndView sobre() {
			ModelAndView mv = new ModelAndView("home/sobre");
			return mv;
			
		}
		
		

}
