package com.sj.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.format.FormatterRegistry;
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

import com.sj.repository.converter.StringToAcitvateEnumConverter;
import com.sj.repository.converter.StringToAdvertiseCategoryConverter;
import com.sj.repository.converter.StringToAdvertisementConverter;
import com.sj.repository.converter.StringToContentConverter;
import com.sj.repository.converter.StringToInfoCategoryConverter;
import com.sj.repository.converter.StringToInfoContentConverter;
import com.sj.repository.converter.StringToProductConverter;
import com.sj.repository.converter.StringToProductStatusEnumConverter;
import com.sj.repository.converter.StringToScrollImageTypeConverter;
import com.sj.repository.converter.StringToSubjectCategoryConverter;

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
			http.csrf()
					.disable()
					.authorizeRequests()
					.antMatchers("/admin/**")
					.hasRole("ADMIN")
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
			;
		}

		@Override
		public void configure(AuthenticationManagerBuilder auth)
				throws Exception {
			auth.userDetailsService(service).passwordEncoder(passwordEncoder());
		}

		@Override
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

	/*---------------------------converter bean------------------------------------------*/
	@Override
	public void addFormatters(FormatterRegistry formatterRegistry) {
		formatterRegistry.addConverter(stringToAcitvateEnumConverter());
		formatterRegistry.addConverter(stringToAdvertisementConverter());
		formatterRegistry.addConverter(stringToScrollImageTypeConverter());
		formatterRegistry.addConverter(stringToAdvertiseCategoryConverter());
		formatterRegistry.addConverter(stringToProductConverter());
		formatterRegistry.addConverter(stringToInfoCategoryConverter());
		formatterRegistry.addConverter(stringToInfoContentConverter());
		formatterRegistry.addConverter(stringToContentConverter());
		formatterRegistry.addConverter(stringToProductStatusEnumConverter());
		formatterRegistry.addConverter(stringToSubjectCategoryConverter());
	}

	@Bean
	public StringToAcitvateEnumConverter stringToAcitvateEnumConverter() {
		return new StringToAcitvateEnumConverter();
	}

	@Bean
	public StringToAdvertisementConverter stringToAdvertisementConverter() {
		return new StringToAdvertisementConverter();
	}

	@Bean
	public StringToAdvertiseCategoryConverter stringToAdvertiseCategoryConverter() {
		return new StringToAdvertiseCategoryConverter();
	}

	@Bean
	public StringToScrollImageTypeConverter stringToScrollImageTypeConverter() {
		return new StringToScrollImageTypeConverter();
	}

	@Bean
	public StringToProductConverter stringToProductConverter() {
		return new StringToProductConverter();
	}

	@Bean
	public StringToInfoCategoryConverter stringToInfoCategoryConverter() {
		return new StringToInfoCategoryConverter();
	}

	@Bean
	public StringToInfoContentConverter stringToInfoContentConverter() {
		return new StringToInfoContentConverter();
	}

	@Bean
	public StringToContentConverter stringToContentConverter() {
		return new StringToContentConverter();
	}

	@Bean
	public StringToProductStatusEnumConverter stringToProductStatusEnumConverter() {
		return new StringToProductStatusEnumConverter();
	}

	@Bean
	public StringToSubjectCategoryConverter stringToSubjectCategoryConverter() {
		return new StringToSubjectCategoryConverter();
	}

	/*---------------------------end converter bean---------------------------------------*/

	@Override
	public void addArgumentResolvers(
			List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
	}
}
