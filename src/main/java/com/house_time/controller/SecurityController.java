package com.house_time.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	
	@GetMapping("/")
	public String inicio() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login(@AuthenticationPrincipal User user) {
		if(user != null) {
			return "redirect:/index";
		}
		return "login";
	}
	

}
