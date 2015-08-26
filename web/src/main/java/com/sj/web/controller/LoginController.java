package com.sj.web.controller;

import java.util.List;
import java.util.Set;

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
import com.sj.model.model.CartLine;
import com.sj.model.model.CommonUser;
import com.sj.model.model.Provider;
import com.sj.model.model.SiteUser;
import com.sj.model.model.UserIndustryInfo;
import com.sj.repository.service.CartLineService;
import com.sj.repository.service.CityService;
import com.sj.repository.service.CommonUserService;
import com.sj.repository.service.ProviderService;
import com.sj.repository.service.ProvinceService;
import com.sj.repository.service.SiteUserService;
import com.sj.repository.service.UserIndustryInfoService;
import com.sj.repository.util.ChangePasswordForm;
import com.sj.repository.util.RetrievePasswordForm;
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
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private CartLineService cartLineService;
	@Autowired
	private UserIndustryInfoService userIndustryInfoService;
	@Autowired
	private CityService cityService;

	private final String LOGIN = "user/login";
	private final String COMMONSIGNUP = "user/common/signup";
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
	public String login(@Valid @ModelAttribute("user") SiteUser user,
			BindingResult bindingResult, Model uiModel, HttpSession httpSession) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				user.getName(), user.getPassword());
		try {
			authenticationManager.authenticate(token);
		} catch (BadCredentialsException exception) {
			user.setPassword(null);
			bindingResult.addError(new FieldError("user", "error", "用户名或密码错误"));
			uiModel.addAttribute("user", user);
			return LOGIN;
		}
		user = (SiteUser) userDetailsService.loadUserByUsername(user.getName());
		userContext.setCurrentUser(user);
		Set<CartLine> lines = cartLineService.findByUser(user.getId());
		httpSession.setAttribute("cartLines", lines);
		return "redirect:/index";
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

	/* user registered page */
	@RequestMapping(value = "/signup", method = RequestMethod.GET, params = "user")
	public String signupForm(Model uiModel) {
		uiModel.addAttribute("user", new CommonUser());
		uiModel.addAttribute("provinces", provinceService.findAll());
		List<UserIndustryInfo> infos = userIndustryInfoService.findAll();
		uiModel.addAttribute("infos", infos);
		return COMMONSIGNUP;
	}

	/* user registered */
	@RequestMapping(value = "/signup", method = RequestMethod.POST, params = "user")
	public String signupProcess(@Valid @ModelAttribute("user") CommonUser user,
			BindingResult userResult, HttpSession session, Model uiModel) {
		if (userResult.hasErrors()) {
			user.setPassword(null);
			uiModel.addAttribute("provinces", provinceService.findAll());
			if(user.getProvince()!=null){
				uiModel.addAttribute("citys", cityService.findByProvince(user.getProvince()));
			}
			uiModel.addAttribute("user", user);
			return COMMONSIGNUP;
		}
		user.setPassword(encoder.encodePassword(user.getPassword(), null));
		SiteUser siteUser = commonUserService.create(user);
		userContext.setCurrentUser(siteUser);
		return "redirect:/";
	}

	/* provider registered page */
	@RequestMapping(value = "/signup", method = RequestMethod.GET, params = "provider")
	public String providerSignupForm(Model uiModel) {
		uiModel.addAttribute("user", new Provider());
		return PSIGNUP;
	}

	/* provider registered */
	@RequestMapping(value = "/signup", method = RequestMethod.POST, params = "provider")
	public String providerSignupProcess(
			@Valid @ModelAttribute("user") Provider provider,
			BindingResult providerResult, HttpSession session, Model uiModel) {
		provider.setPassword(encoder.encodePassword(provider.getPassword(),
				null));
		SiteUser user = providerService.create(provider);
		userContext.setCurrentUser(user);
		return HOME;
	}

	private void validateSignupForm(String captcha, BindingResult result,
			HttpSession session) {
		String kaptcha = (String) session
				.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (!StringUtils.equals(captcha, kaptcha)) {
			result.addError(new FieldError("user", "captcha", captchaError));
		}
	}

	/* Verify that the name exists */
	@RequestMapping(value = "/name", method = RequestMethod.POST,params="valid")
	@ResponseBody
	public String validateUserNameIsExiste(@RequestParam("name") String name) {
		SiteUser user = userService.findByName(name);
		if (user == null) {
			return "true";
		}
		return "false";
	}

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

	/* forget password */
	@RequestMapping(value = { "/provider/forgetPw", "/siteUser/forgetPw" }, method = RequestMethod.GET)
	public String forgetPw(Model uiModel) {

		return "index";
	}

	@RequestMapping(value = { "/provider/forgetPw", "/siteUser/forgetPw" }, method = RequestMethod.POST)
	public String forgetPwVaildata(
			@ModelAttribute("retrieve") RetrievePasswordForm form, Model uiModel) {
		uiModel.addAttribute("form", new RetrievePasswordForm("13700000001"));
		return "user/changePassword";
	}

	@RequestMapping(value = { "/provider/forgetPw", "/siteUser/forgetPw" }, method = RequestMethod.PUT)
	public String forgetPwProcess(
			@ModelAttribute("retrieve") RetrievePasswordForm form,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {

		}
		SiteUser user = userService.findByPhone(form.getPhone());
		userService.updatePassword(user.getId(),
				encoder.encodePassword(form.getPassword(), null));
		SiteUser usert = userService.findByPhone(form.getPhone());
		return "redirect:/login";
	}

	private void sendCaptcha() {

	}

}
