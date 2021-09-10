package com.leichu.app4.controller;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class IndexController {

	@RequestMapping("index")
	public String index(Model model) {
		AttributePrincipal principal = AssertionHolder.getAssertion().getPrincipal();
		System.out.println("APP4获取服务端用户信息：" + principal.getAttributes());
		Map<String, Object> map = principal.getAttributes();
		model.addAttribute("principal", principal.getName());
		model.addAttribute("uid", map.get("uid"));
		model.addAttribute("name", map.get("name"));
		model.addAttribute("who", map.get("who"));
		return "index";
	}
}
