package com.sj.web.controller;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Audience {
	@Pointcut("execution(* com.sj.web.controller.*.*(..))")
	public void test() {
		System.out.println("test。。。。。。");
	}

	@Before("test()")
	public void before() {
		System.out.println("before。。。。。");
	}

	@After("test()")
	public void after() {
		System.out.println("after。。。。。。");
	}
}
