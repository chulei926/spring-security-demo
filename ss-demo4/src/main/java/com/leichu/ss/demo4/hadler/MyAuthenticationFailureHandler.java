package com.leichu.ss.demo4.hadler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录失败处理器.
 *
 * @author leichu 2021-08-12.
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private final String url;
	public MyAuthenticationFailureHandler(String url) {
		this.url = url;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

		response.sendRedirect(url);
	}
}
