package com.sj.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sj.admin.converter.StringToAcitvateEnumConverter;
import com.sj.admin.converter.StringToAdvertiseCategoryConverter;
import com.sj.admin.converter.StringToAdvertisementContent;
import com.sj.admin.converter.StringToProductConverter;
import com.sj.admin.converter.StringToScrollImageTypeConverter;
import com.sj.admin.converter.StringToSubjectCategoryConverter;
import com.sj.admin.resolver.PageRequestResolver;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Import(value = { com.sj.repository.Application.class,
		com.sj.model.Application.class })
public class Application extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfig extends WebSecurityConfigurerAdapter {
		@Resource
		private UserDetailsService service;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
					.anyRequest().permitAll().and().formLogin()
					.defaultSuccessUrl("/index")
					.loginProcessingUrl("/loginProcess")
					.usernameParameter("name").passwordParameter("password")
					.loginPage("/login").failureUrl("/login?error").and()
					.logout().logoutSuccessUrl("/index").logoutUrl("/logout")
					.permitAll();
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

	/*---------------------------converter bean------------------------------------------*/
	@Override
	public void addFormatters(FormatterRegistry formatterRegistry) {
		formatterRegistry.addConverter(stringToAcitvateEnumConverter());
		formatterRegistry.addConverter(stringToAdvertisementContent());
		formatterRegistry.addConverter(stringToScrollImageTypeConverter());
		formatterRegistry.addConverter(stringToSubjectCategoryConverter());
		formatterRegistry.addConverter(stringToAdvertiseCategoryConverter());
		formatterRegistry.addConverter(stringToProductConverter());
	}

	@Bean
	public StringToAcitvateEnumConverter stringToAcitvateEnumConverter() {
		return new StringToAcitvateEnumConverter();
	}

	@Bean
	public StringToAdvertisementContent stringToAdvertisementContent() {
		return new StringToAdvertisementContent();
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
	public StringToSubjectCategoryConverter stringToSubjectCategoryConverter() {
		return new StringToSubjectCategoryConverter();
	}

	@Bean
	public StringToProductConverter stringToProductConverter() {
		return new StringToProductConverter();
	}

	/*---------------------------end converter bean---------------------------------------*/

	@Override
	public void addArgumentResolvers(
			List<HandlerMethodArgumentResolver> argumentResolvers) {
		PageRequestResolver pageRequestResolver = new PageRequestResolver();
		argumentResolvers.add(pageRequestResolver);
	}
}
