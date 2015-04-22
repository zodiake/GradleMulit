package com.sj.web.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.sj.model.model.SiteUser;
import com.sj.repository.service.SiteUserService;
import com.sj.repository.util.ChangePasswordForm;
import com.sj.repository.util.SignupForm;
import com.sj.web.security.SiteUserContext;

@Controller
public class LoginController {
	@Autowired
	private SiteUserContext userContext;
	@Autowired
	private ShaPasswordEncoder encoder;
	@Autowired
	private SiteUserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;

	private final String LOGIN = "login";
	private final String SIGNUP = "user/signup";
	private final String HOME = "index";
	private final String CHANGEPASSWORD = "user/changePassword";
	private final String AJAXLOGIN = "ajaxLogin";

	@Value("${login.passwordError}")
	private String passwordError;

	@Value("${login.captchaError}")
	private String captchaError;

	@Value("${login.oldPasswordError}")
	private String oldPasswordError;

	/* login and signup logic */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return LOGIN;
	}

	@RequestMapping(value = "/ajaxLogin", method = RequestMethod.GET)
	public String ajaxLoginPage() {
		return AJAXLOGIN;
	}

	@RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String ajaxLogin(
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "password", required = true) String password) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				name, password);
		try {
			authenticationManager.authenticate(token);
		} catch (BadCredentialsException exception) {
			return "fail";
		}
		SiteUser user = (SiteUser) userDetailsService.loadUserByUsername(name);
		userContext.setCurrentUser(user);
		return "success";
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
			result.addError(new FieldError("SignupForm", "confirm",
					passwordError));
		}
		if (!StringUtils.equals(form.getCaptcha(), kaptcha)) {
			result.addError(new FieldError("SignupForm", "captcha",
					captchaError));
		}
	}

	/* end login and signup logic */

	/* user change password feature */
	@RequestMapping(value = { "/provider/changePw", "/manufacture/changePw" }, method = RequestMethod.GET)
	public String editPassword(Model uiModel) {
		uiModel.addAttribute("form", new ChangePasswordForm());
		return CHANGEPASSWORD;
	}

	@RequestMapping(value = { "/provider/changePw", "/manufacture/changePw" }, method = RequestMethod.POST)
	public String processPassword(
			@Valid @ModelAttribute("form") ChangePasswordForm form,
			BindingResult result, Model uiModel) {
		SiteUser user = userContext.getCurrnetUser();

		form = translatePassword(form);

		validateChangePassword(user, form, result);
		if (result.hasErrors()) {
			uiModel.addAttribute("form", new ChangePasswordForm());
			return CHANGEPASSWORD;
		}
		userService.updatePassword(user.getId(), form.getNewPassword());
		// todo
		return "index";
	}

	private ChangePasswordForm translatePassword(ChangePasswordForm form) {
		ChangePasswordForm temp = new ChangePasswordForm();
		temp.setOldPassword(encoder.encodePassword(form.getOldPassword(), null));
		temp.setNewPassword(encoder.encodePassword(form.getNewPassword(), null));
		temp.setConfirmPassword(encoder.encodePassword(
				form.getConfirmPassword(), null));
		return temp;
	}

	private void validateChangePassword(SiteUser user, ChangePasswordForm form,
			BindingResult result) {
		if (!StringUtils.equals(user.getPassword(), form.getOldPassword())) {
			result.addError(new FieldError("ChangePasswordForm", "oldPassword",
					oldPasswordError));
		}
		if (!StringUtils.equals(form.getNewPassword(),
				form.getConfirmPassword())) {
			result.addError(new FieldError("ChangePasswordForm",
					"confirmPassword", passwordError));
		}
	}

	/* end user change password feature */

	/* forget password */
	@RequestMapping(value = { "/provider/forgetPw", "/manufacture/forgetPw" }, method = RequestMethod.GET)
	public String forgetPw(@RequestParam("captcha") String captcha) {
		// todo
		return "index";
	}

	private void sendCaptcha() {

	}
	/* end forget password */

}
