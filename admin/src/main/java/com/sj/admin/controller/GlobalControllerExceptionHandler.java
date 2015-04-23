package com.sj.admin.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sj.admin.exception.UserNotFoundException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	public static final String NOTFOUND = "error/notFound";
	public static final String NOAUTHORITY = "error/noAuthority";

	@ExceptionHandler(UserNotFoundException.class)
	public String postNotFound() {
		return NOTFOUND;
	}

}