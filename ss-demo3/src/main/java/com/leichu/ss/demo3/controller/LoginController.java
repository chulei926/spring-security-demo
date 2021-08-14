package com.leichu.ss.demo3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

//	@RequestMapping(value="/login")
//	public String login(){
//		return "redirect:main.html";
//	}

	@RequestMapping(value = "/toMain")
	public String toMain(){
		return "redirect:main.html";
	}

	@RequestMapping(value = "/toError")
	public String toError(){
		return "redirect:error.html";
	}
}
