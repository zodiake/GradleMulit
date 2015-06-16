package com.sj.web.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sj.web.exception.NoAuthorityException;
import com.sj.web.exception.ProductNotFoundException;
import com.sj.web.exception.UserNotFoundException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	public static final String NOTFOUND = "error/notFound";
	public static final String NOAUTHORITY = "error/noAuthority";

	@ExceptionHandler(UserNotFoundException.class)
	public String userNotFound() {
		return NOTFOUND;
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public String productNotFound() {
		return NOTFOUND;
	}

	@ExceptionHandler(NoAuthorityException.class)
	public String noAuthority() {
		return NOAUTHORITY;
	}

}