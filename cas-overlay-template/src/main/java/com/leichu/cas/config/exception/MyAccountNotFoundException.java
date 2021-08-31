package com.leichu.cas.config.exception;

import javax.security.auth.login.AccountException;

public class MyAccountNotFoundException extends AccountException {
	public MyAccountNotFoundException() {
		super();
	}

	public MyAccountNotFoundException(String msg) {
		super(msg);
	}
}
