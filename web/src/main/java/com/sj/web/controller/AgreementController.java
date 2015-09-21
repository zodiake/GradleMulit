package com.sj.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class AgreementController {
	@RequestMapping(value = "/regirectAgreement",method=RequestMethod.GET)
	public String findRegirectAgreement(){
		return "";
	}
}
