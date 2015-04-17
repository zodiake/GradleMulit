package com.sj.web.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.code.kaptcha.Constants;
import com.sj.repository.util.SignupForm;
import com.sj.web.security.SiteUserContext;

@Controller
public class LoginController {
	@Autowired
	private SiteUserContext userContext;

	private final String LOGIN = "login";
	private final String SIGNUP = "user/signup";
	private final String HOME = "index";

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return LOGIN;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupForm(Model uiModel) {
		SignupForm form = new SignupForm();
		uiModel.addAttribute("form", form);
		return SIGNUP;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupProcess(@Valid @ModelAttribute("form") SignupForm form,
			BindingResult result, HttpSession session, Model uiModel) {
		validateSignupForm(form, result, session);
		if (result.hasErrors()) {
			form.setConfirm(null);
			form.setPassword(null);
			form.setCaptcha(null);
			uiModel.addAttribute("form", form);
			return SIGNUP;
		}
		return HOME;
	}

	private void validateSignupForm(SignupForm form, BindingResult result,
			HttpSession session) {
		String kaptcha = (String) session
				.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (!StringUtils.equals(form.getPassword(), form.getConfirm())) {
			result.addError(new FieldError("SignupForm", "confirm", "两次密码输入不一致"));
		}
		if (!StringUtils.equals(form.getCaptcha(), kaptcha)) {
			result.addError(new FieldError("SignupForm", "captcha", "请输入正确的验证码"));
		}
	}

}
