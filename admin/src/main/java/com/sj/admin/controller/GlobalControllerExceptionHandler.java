package com.sj.admin.controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sj.admin.exception.CategoryNotFoundException;
import com.sj.admin.exception.UserNotFoundException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	public static final String NOTFOUND = "error/notFound";
	public static final String NOAUTHORITY = "error/noAuthority";
	public static final String NUMBERFORMAT = "error/numberFormat";

	@ExceptionHandler(UserNotFoundException.class)
	public String postNotFound() {
		return NOTFOUND;
	}

	@ExceptionHandler(TypeMismatchException.class)
	public String numberFormatException() {
		return NUMBERFORMAT;
	}

	@ExceptionHandler(CategoryNotFoundException.class)
	public String categoryNotFound(){
		return NOTFOUND;
	}

}