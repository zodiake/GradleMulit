package com.sj.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sj.web.resolver.PageRequestResolver;
import com.sj.web.resolver.SiteUserResolver;

@EnableConfigurationProperties
@SpringBootApplication
@Import(value = { com.sj.repository.Application.class,
		com.sj.model.Application.class })
public class Application extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfig extends WebSecurityConfigurerAdapter {
		@Autowired
		private UserDetailsService service;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
					.antMatchers("/provider/**")
					.hasRole("PROVIDER")
					.antMatchers("/user/**")
					.hasRole("COMMONUSER")
					.anyRequest()
					.permitAll()
					.and()
					.formLogin()
					.defaultSuccessUrl("/index")
					.loginProcessingUrl("/loginProcess")
					.usernameParameter("name")
					.passwordParameter("password")
					.loginPage("/login")
					.failureUrl("/login?error")
					.and()
					.logout()
					.logoutSuccessUrl("/index")
					.logoutUrl("/logout")
					.permitAll()
					.and()
					.headers()
					.addHeaderWriter(
							new XFrameOptionsHeaderWriter(
									XFrameOptionsMode.SAMEORIGIN));
		}

		@Override
		public void configure(AuthenticationManagerBuilder auth)
				throws Exception {
			auth.userDetailsService(service).passwordEncoder(passwordEncoder());
		}

		@Bean
		public AuthenticationManager authenticationManagerBean()
				throws Exception {
			return super.authenticationManagerBean();
		}

		@Bean
		public ShaPasswordEncoder passwordEncoder() throws Exception {
			return new ShaPasswordEncoder(256);
		}
	}

	@Bean
	public ConversionServiceFactoryBean conversionService() {
		ConversionServiceFactoryBean factoryBean = new ConversionServiceFactoryBean();
		Set<Converter> converters = new HashSet<Converter>();
		factoryBean.setConverters(converters);
		return factoryBean;
	}

	@Override
	public void addArgumentResolvers(
			List<HandlerMethodArgumentResolver> argumentResolvers) {
		SiteUserResolver personResolver = new SiteUserResolver();
		PageRequestResolver pageRequestResolver = new PageRequestResolver();
		argumentResolvers.add(personResolver);
		argumentResolvers.add(pageRequestResolver);
	}
}
