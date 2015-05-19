package com.sj.repository;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

@Configuration
@EnableAutoConfiguration
@EnableCaching
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

	@Bean
	public CacheManager getEhCacheManager() {
		return new EhCacheCacheManager(getEhCacheFactory().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean getEhCacheFactory() {
		EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
		factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		factoryBean.setShared(true);
		return factoryBean;
	}
}