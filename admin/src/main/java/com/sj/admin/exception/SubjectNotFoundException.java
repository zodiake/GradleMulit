package com.sj.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="subject Not Found")
public class SubjectNotFoundException extends RuntimeException {

}
