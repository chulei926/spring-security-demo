package com.leichu.ss.demo5.hadler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义 403 处理方案.
 *
 * @author leichu 2021-08-12.
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType("application/json;charset=UTF-8");
		JSONObject res = new JSONObject();
		res.put("status", 403);
		res.put("msg", "权限不足");
		final PrintWriter writer = response.getWriter();
		writer.write(res.toJSONString());
		writer.flush();
		writer.close();
	}
}
