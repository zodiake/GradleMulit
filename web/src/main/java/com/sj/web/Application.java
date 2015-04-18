package com.sj.web;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableConfigurationProperties
@SpringBootApplication
@Import(value = {com.sj.repository.Application.class,
        com.sj.model.Application.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Resource
        private UserDetailsService service;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/provider/**").hasRole("PROVIDER")
                    .antMatchers("/manufacturer/**").hasRole("MANUFACTURER")
                    .anyRequest().permitAll().and().formLogin()
                    .defaultSuccessUrl("/index")
                    .usernameParameter("name").passwordParameter("password")
                    .loginPage("/login").failureUrl("/login?error")
                    .permitAll();
        }

        @Override
        public void configure(AuthenticationManagerBuilder auth)
                throws Exception {
            auth.userDetailsService(service).passwordEncoder(passwordEncoder());
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
}
