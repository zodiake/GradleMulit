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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.sj.model.model.CommonUser;
import com.sj.model.model.Provider;
import com.sj.model.model.SiteUser;
import com.sj.repository.service.CommonUserService;
import com.sj.repository.service.ProviderService;
import com.sj.repository.service.SiteUserService;
import com.sj.repository.util.ChangePasswordForm;
import com.sj.repository.util.SignupForm;
import com.sj.web.annotation.SecurityUser;
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
	@Autowired
	private CommonUserService commonUserService;
	@Autowired
	private ProviderService providerService;

	private final String LOGIN = "login";
	private final String SIGNUP = "user/signup";
	private final String PSIGNUP = "user/provider/signup";
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
	/* strat modify */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model uiModel) {
		uiModel.addAttribute("user", new SiteUser());
		return LOGIN;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") SiteUser user,
			BindingResult bindingResult, Model uiModel) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				user.getName(), user.getPassword());
		try {
			authenticationManager.authenticate(token);
		} catch (BadCredentialsException exception) {
			// 在此处添加错误信息
			user.setPassword(null);
			uiModel.addAttribute("user", user);
			bindingResult.addError(new FieldError("user", "name", "用户名或密码错误"));
			return LOGIN;
		}
		user = (SiteUser) userDetailsService.loadUserByUsername(user.getName());
		userContext.setCurrentUser(user);
		return HOME;
	}

	@RequestMapping(value = "/ajaxLogin", method = RequestMethod.GET)
	public String ajaxLoginPage() {
		return AJAXLOGIN;
	}

	@RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
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

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		SiteUser user = userService.findByName("123456");
		System.out.println(user.getName());
		System.out.println(user.getPassword());
		return HOME;
	}

	/* user registered page */
	@RequestMapping(value = "/user/signup", method = RequestMethod.GET)
	public String signupForm(Model uiModel) {
		uiModel.addAttribute("user", new CommonUser());
		return SIGNUP;
	}

	/* user registered */
	@RequestMapping(value = "/user/signup", method = RequestMethod.POST)
	public String signupProcess(@Valid @ModelAttribute("user") CommonUser user,
			BindingResult userResult, HttpSession session, Model uiModel) {
		validateSignupForm(user.getCaptcha(), userResult, session);
		if (userResult.hasErrors()) {
			// form.setConfirm(null);
			user.setPassword(null);
			uiModel.addAttribute("user", user);
			return SIGNUP;
		}
		user.setPassword(encoder.encodePassword(user.getPassword(), null));
		SiteUser siteUser = commonUserService.create(user);
		userContext.setCurrentUser(siteUser);
		return HOME;
	}

	/* provider registered page */
	@RequestMapping(value = "/provider/signup", method = RequestMethod.GET)
	public String providerSignupForm(Model uiModel) {
		uiModel.addAttribute("user", new Provider());
		return PSIGNUP;
	}

	/* provider registered */
	@RequestMapping(value = "/provider/signup", method = RequestMethod.POST)
	public String providerSignupProcess(
			@Valid @ModelAttribute("user") Provider provider,
			BindingResult providerResult, HttpSession session, Model uiModel) {
		validateSignupForm(provider.getCaptcha(), providerResult, session);

		if (providerResult.hasErrors()) {
			// form.setConfirm(null);
			provider.setPassword(null);
			uiModel.addAttribute("from", new SignupForm());
			uiModel.addAttribute("user", provider);
			return PSIGNUP;
		}
		provider.setPassword(encoder.encodePassword(provider.getPassword(),
				null));
		SiteUser user = providerService.create(provider);
		userContext.setCurrentUser(user);
		return HOME;
	}

	// private void validateSignupForm(SignupForm form, BindingResult result,
	// HttpSession session) {
	// String kaptcha = (String) session
	// .getAttribute(Constants.KAPTCHA_SESSION_KEY);
	// if (!StringUtils.equals(form.getPassword(), form.getConfirm())) {
	// result.addError(new FieldError("SignupForm", "confirm",
	// passwordError));
	// }
	// if (!StringUtils.equals(form.getCaptcha(), kaptcha)) {
	// result.addError(new FieldError("SignupForm", "captcha",
	// captchaError));
	// }
	// }
	private void validateSignupForm(String captcha, BindingResult result,
			HttpSession session) {
		String kaptcha = (String) session
				.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (!StringUtils.equals(captcha, kaptcha)) {
			result.addError(new FieldError("user", "captcha", captchaError));
		}
	}

	/* Verify that the user name exists */
	@RequestMapping(value = "/user/isExiste/{name}", method = RequestMethod.GET)
	@ResponseBody
	public boolean validateUserNameIsExiste(@PathVariable("name") String name) {
		SiteUser user = userService.findByName(name);
		if (user == null) {
			return true;
		}
		return false;
	}

	/* end modify */
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
			BindingResult result, Model uiModel, @SecurityUser SiteUser user) {
		ChangePasswordForm source = form;
		form = translatePassword(form);

		validateChangePassword(user, form, result);
		if (result.hasErrors()) {
			uiModel.addAttribute("form", source);
			return CHANGEPASSWORD;
		}
		userService.updatePassword(user.getId(), form.getNewPassword());
		// todo
		return "redirect:/index";
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
		// todo

	}
	/* end forget password */

}
