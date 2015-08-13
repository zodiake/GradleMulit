package com.sj.web.controller;

import javax.servlet.http.HttpSession;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.code.kaptcha.Constants;
import com.sj.model.model.SiteUser;
import com.sj.repository.service.SiteUserService;
import com.sj.repository.util.PhoneCaptchaForm;
import com.sj.repository.util.RetrievePasswordForm;
import com.sj.web.exception.UserNotFoundException;
import com.sj.web.security.SiteUserContext;

@Controller
public class SiteUserController {
	@Autowired
	private SiteUserService siteUserService;
	@Autowired
	private SiteUserContext userContext;
	@Autowired
	private ShaPasswordEncoder encoder;

	private final String SITEUSER = "";

	@RequestMapping(value = "/currentUsers", method = RequestMethod.GET)
	public String findCurrentUser(Model uiModel) {
		SiteUser user = userContext.getCurrentUser();
		if (user == null)
			throw new UserNotFoundException();
		uiModel.addAttribute("siteUser", user);
		return SITEUSER;
	}

	@RequestMapping(value = "/user/password/retrieve", method = RequestMethod.GET)
	public String retrievePasswordPage(Model uiModel) {
		uiModel.addAttribute("form", new PhoneCaptchaForm());
		return null;
	}

	@RequestMapping(value = "/user/password/retrieve", method = RequestMethod.POST)
	public String retrievePassword(
			@ModelAttribute("form") PhoneCaptchaForm phoneCaptchaForm,
			BindingResult bindingResult, Model uiModel, HttpSession session) {
		validatePhoneCaptchaForm(phoneCaptchaForm, bindingResult, session);
		if (bindingResult.hasErrors()) {
			phoneCaptchaForm.setCaptcha(null);
			uiModel.addAttribute("from", phoneCaptchaForm);
			return null;
		}
		uiModel.addAttribute("form",
				new RetrievePasswordForm(phoneCaptchaForm.getPhone()));
		return null;
	}

	@RequestMapping(value = "/user/password/retrieve", method = RequestMethod.PUT)
	public String changePassword(
			@ModelAttribute("form") RetrievePasswordForm form,
			BindingResult result, Model uiModel) {
		validateRetrievePasswordForm(form, result);
		if (result.hasErrors()) {
			form.setConfirm(null);
			form.setPassword(null);
			uiModel.addAttribute("form", form);
			return null;
		}
		siteUserService.retrievePassword(form.getPhone(), encoder.encodePassword(form.getPassword(), null));
		return null;
	}

	private void validateRetrievePasswordForm(RetrievePasswordForm form,
			BindingResult result) {
		if (form.getPhone().length() != 11) {
			result.addError(new FieldError("form", "password", "系统异常"));
		}
		if (!StringUtils.equals(form.getPassword(), form.getConfirm())) {
			result.addError(new FieldError("form", "confirm", "请确认两次输入的密码不一致"));
		}
	}

	private void validatePhoneCaptchaForm(PhoneCaptchaForm phoneCaptchaForm,
			BindingResult result, HttpSession session) {
		String kaptcha = (String) session
				.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (!StringUtils.equals(phoneCaptchaForm.getCaptcha(), kaptcha)) {
			result.addError(new FieldError("form", "captcha", "验证码错误"));
		}
		SiteUser user = siteUserService
				.findByPhone(phoneCaptchaForm.getPhone());
		if (user == null) {
			result.addError(new FieldError("form", "phone", "手机号码错误"));
		}
	}
}
