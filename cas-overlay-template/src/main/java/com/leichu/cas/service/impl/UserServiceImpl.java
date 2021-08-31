package com.leichu.cas.service.impl;

import com.leichu.cas.dao.UserDao;
import com.leichu.cas.model.User;
import com.leichu.cas.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public User login(String username, String password) {
		return userDao.find(username, password);
	}
}
