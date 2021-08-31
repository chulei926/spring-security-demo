package com.leichu.cas.service;

import com.leichu.cas.model.User;

public interface UserService {

	User login(String username, String password);

}
