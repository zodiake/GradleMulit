package com.sj.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "buyRecord not found")
public class BuyRecordNotFoundException extends RuntimeException {

}
