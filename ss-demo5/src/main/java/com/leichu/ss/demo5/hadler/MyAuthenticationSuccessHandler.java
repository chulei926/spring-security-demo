package com.leichu.ss.demo5.hadler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录成功处理器.
 *
 * @author leichu 2021-08-12.
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private final String url;

	public MyAuthenticationSuccessHandler(String url) {
		this.url = url;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		response.sendRedirect(url);
	}
}
