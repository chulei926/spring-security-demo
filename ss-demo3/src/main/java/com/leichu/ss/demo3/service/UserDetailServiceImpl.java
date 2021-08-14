package com.leichu.ss.demo3.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 自定义登录逻辑.
 *
 * @author leichu 2021-08-12.
 */
@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

	// 前端输入的用户名
	private String name="admin";
	// 前端输入的密码
	private String pwd = "111";

	@Resource
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (!name.equals(username)){
			throw new UsernameNotFoundException("用户名不存在");
		}
		final String password = passwordEncoder.encode(pwd);
		return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,subAdmin,normal"));
	}
}
