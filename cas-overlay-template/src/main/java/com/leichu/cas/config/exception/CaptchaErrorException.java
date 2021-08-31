package com.leichu.cas.config.exception;

import javax.security.auth.login.AccountException;

public class CaptchaErrorException extends AccountException {
	public CaptchaErrorException() {
		super();
	}

	public CaptchaErrorException(String msg) {
		super(msg);
	}
}
