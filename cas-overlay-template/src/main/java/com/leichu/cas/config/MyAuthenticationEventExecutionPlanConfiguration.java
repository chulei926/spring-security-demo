package com.leichu.cas.config;

import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.PrePostAuthenticationHandler;
import org.apereo.cas.authentication.principal.DefaultPrincipalFactory;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.services.ServicesManager;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration("MyAuthenticationEventExecutionPlanConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
@ComponentScan(basePackages = "com.leichu.cas")
public class MyAuthenticationEventExecutionPlanConfiguration implements AuthenticationEventExecutionPlanConfigurer {

	@Resource
	private ServicesManager servicesManager;

	@Bean
	public SpringContextHolder springContextHolder() {
		System.out.println("初始化 SpringContextHolder");
		return new SpringContextHolder();
	}

	@Bean
	public PrePostAuthenticationHandler myAuthenticationHandler() {
		// 定义为优先使用它进行认证
		System.out.println("创建 myAuthenticationHandler");
		return new MyAuthenticationHandler(MyAuthenticationHandler.class.getName(), servicesManager, new DefaultPrincipalFactory(), 1);
	}

	@Override
	public void configureAuthenticationExecutionPlan(AuthenticationEventExecutionPlan plan) {
		System.out.println("注册 myAuthenticationHandler");
		plan.registerAuthenticationHandler(myAuthenticationHandler());
	}
}
