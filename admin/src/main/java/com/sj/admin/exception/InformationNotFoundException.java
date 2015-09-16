package com.sj.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="information Not Found")
public class InformationNotFoundException extends RuntimeException {

}
