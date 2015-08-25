package com.sj.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.admin.security.SiteUserContext;
import com.sj.model.model.SiteUser;
import com.sj.repository.service.SiteUserService;

@Controller
public class LoginController {
	@Autowired
	private SiteUserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private SiteUserContext userContext;

	private static final String LOGIN = "login";
	private static final String INDEX = "index";

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model uiModel) {
		uiModel.addAttribute("user", new SiteUser());
		return LOGIN;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute("user") SiteUser user,
			BindingResult bindingResult, Model uiModel) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				user.getName(), user.getPassword());
		try {
			authenticationManager.authenticate(token);
		} catch (BadCredentialsException exception) {
			user.setPassword(null);
			uiModel.addAttribute("user", user);
			bindingResult.addError(new FieldError("user", "error", "用户名或密码错误"));
			return LOGIN;
		}
		SiteUser siteUser = (SiteUser) userDetailsService
				.loadUserByUsername(user.getName());
		if ("ROLE_ADMIN".equals(siteUser.getSiteAuthority())) {
			userContext.setCurrentUser(siteUser);
			return INDEX;
		} else {
			user.setPassword(null);
			uiModel.addAttribute("user", user);
			bindingResult.addError(new FieldError("user", "error", "对不起您没有权限"));
		}
		return LOGIN;
	}
}
