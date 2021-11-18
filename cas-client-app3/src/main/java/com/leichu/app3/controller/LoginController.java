package com.leichu.app3.controller;

import com.alibaba.fastjson.JSONObject;
import com.leichu.common.dto.JsonResult;
import com.leichu.common.exception.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

	private static final String name = "lei";
	private static final String pwd = "123";

	@RequestMapping("login")
	@ResponseBody
	public JsonResult<JSONObject> login(HttpSession session, String username, String password) {
		if (name.equals(username) && pwd.equals(password)) {
			final JSONObject res = new JSONObject().fluentPut("acc", name).fluentPut("pwd", pwd);
			session.setAttribute("curUser", res);
			return JsonResult.getSuccessResult(res);
		}
		throw new AuthenticationException("用户名或密码错误");
	}

}
