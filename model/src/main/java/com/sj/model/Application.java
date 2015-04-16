package com.sj.model;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "com.sj.model.model")
public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
