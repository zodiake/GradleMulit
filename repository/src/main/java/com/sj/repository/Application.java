package com.sj.repository;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.sj")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public DefaultKaptcha captchaProducer() {
		DefaultKaptcha kaptcha = new DefaultKaptcha();
		Properties properties = new Properties();
		properties.put("kaptcha.textproducer.char.string", "0123456789");
		properties.put("kaptcha.textproducer.char.length", "4");
		Config config = new Config(properties);
		kaptcha.setConfig(config);
		return kaptcha;
	}

	@Bean
	public javax.validation.Validator localValidatorFactoryBean() {
	   return new LocalValidatorFactoryBean();
	}
}