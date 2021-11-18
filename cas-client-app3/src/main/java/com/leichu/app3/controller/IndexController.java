package com.leichu.app3.controller;

import com.alibaba.fastjson.JSONObject;
import com.leichu.common.dto.JsonResult;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("api/index")
public class IndexController {

	@RequestMapping("/")
	public String index(Model model) {
		AttributePrincipal principal = AssertionHolder.getAssertion().getPrincipal();
		System.out.println("APP3获取服务端用户信息：" + principal.getAttributes());
		Map<String, Object> map = principal.getAttributes();
		model.addAttribute("principal", principal.getName());
		model.addAttribute("uid", map.get("uid"));
		model.addAttribute("name", map.get("name"));
		model.addAttribute("who", map.get("who"));
		return "index";
	}

	@RequestMapping("getCurrentUser")
	@ResponseBody
	public JsonResult<JSONObject> getCurrentUser(HttpSession session) {
		final Object curUser = session.getAttribute("curUser");
		JSONObject res = new JSONObject();
		if (null != curUser){
			res = (JSONObject) curUser;
		}
		return JsonResult.getSuccessResult(res);
	}

}
