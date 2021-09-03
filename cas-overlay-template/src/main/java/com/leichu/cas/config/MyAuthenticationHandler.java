package com.leichu.cas.config;

import com.leichu.cas.config.exception.MyAccountNotFoundException;
import com.leichu.cas.model.User;
import com.leichu.cas.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.MessageDescriptor;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.UsernamePasswordCredential;
import org.apereo.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;

import javax.security.auth.login.AccountException;
import java.security.GeneralSecurityException;
import java.util.*;

@Log4j2
public class MyAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {

	public MyAuthenticationHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
		super(name, servicesManager, principalFactory, order);
	}


	@Override
	protected AuthenticationHandlerExecutionResult authenticateUsernamePasswordInternal(UsernamePasswordCredential credential, String originalPassword) throws GeneralSecurityException, PreventedException {

		String username = credential.getUsername();
		String password = credential.getPassword();
		log.warn("开始认证.... username:{} password:{}", username, password);

		final UserService userService = SpringContextHolder.getBean(UserService.class);
		final User user = userService.login(username, password);
		if (null == user) {
			throw new MyAccountNotFoundException("抱歉，用户名或密码错误！");
		}

		final List<MessageDescriptor> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("uid", user.getId());
		map.put("name", user.getUsername());
		map.put("who", "张三");
		return createHandlerResult(credential, this.principalFactory.createPrincipal(username, map), list);
	}

}
