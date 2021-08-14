package com.leichu.ss.demo4.config;

import com.leichu.ss.demo4.hadler.MyAccessDeniedHandler;
import com.leichu.ss.demo4.hadler.MyAuthenticationFailureHandler;
import com.leichu.ss.demo4.hadler.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private MyAccessDeniedHandler myAccessDeniedHandler;

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().usernameParameter("my-username").passwordParameter("my-password")
				// 自定义的登录界面
				.loginPage("/login.html")
				// 放行 /login 接口，这里的 /login 必须和 前端form表单的 action 配置的一致
				.loginProcessingUrl("/login")
				// 登陆成功之后跳转的界面 (必须是post请求，否则会出现 type=Method Not Allowed, status=405 )
				// .successForwardUrl("/main.html")
//				.successForwardUrl("/toMain")
//				.failureForwardUrl("/toError")
				.successHandler(new MyAuthenticationSuccessHandler("/main.html"))
				.failureHandler(new MyAuthenticationFailureHandler("/error.html"))

		;
		http.authorizeRequests()
				// 放行 ，不需要认证
				.antMatchers("/login.html").permitAll()
				.antMatchers("/error.html").permitAll()
				// 放行某个目录下所有的文件
				.antMatchers("/css/**", "/js/**", "/images/**").permitAll()
				// 放行指定的后缀名文件
				.antMatchers("/**/*.png", "/**/*.jpg").permitAll()
				// 正则匹配
				// .regexMatchers(HttpMethod.GET, "/demo").permitAll()
				// .regexMatchers(HttpMethod.POST, "/demo").permitAll()
				// mvc 匹配
				// .mvcMatchers("/demo").servletPath("/xxx").permitAll() // http://localhost:8080/xxx/demo
				// 权限判断 只有拥有 leichu 权限的用户才能访问
//				.antMatchers("/leichu.html").hasAuthority("leichu") // hasAuthority() 指是否拥有某一个权限
//				.antMatchers("/leichu.html").hasAnyAuthority("leichu", "leichu2") // hasAnyAuthority() 指是否拥有多个权限
//				.antMatchers("/leichu.html").hasRole("abcd")
//				.antMatchers("/leichu.html").hasAnyRole("abc", "def")
				.antMatchers("/leichu.html").hasIpAddress("127.0.0.1")
				// 拦截所有请求，必须登录才能访问
				.anyRequest().authenticated();
		// myAccessDeniedHandler 需要注入进来  不能直接 new
		http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);

		http.csrf().disable();
	}
}
