package com.sj.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
public class DeferredResultController {
	@RequestMapping("/deferred")
	@ResponseBody
	public DeferredResult<String> deferred(){
		DeferredResult<String> deferred=new DeferredResult<>();
		deferred.setResult("abc");
		return deferred;
	}

}
