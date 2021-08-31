package com.leichu.app1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	@RequestMapping("logout")
	public String logout(Model model) {
		model.addAttribute("url", "https://leichu.com:8443/sso/logout?service=http%3A%2F%2Fapp1.com%3A8080%2Fapp1%2F");
		return "logout";
	}

}
