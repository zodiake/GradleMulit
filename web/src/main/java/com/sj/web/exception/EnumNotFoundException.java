package com.sj.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="enum Not Found")
public class EnumNotFoundException extends RuntimeException {

}
