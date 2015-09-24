package com.sj.web.controller;

import java.util.HashSet;
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
import com.sj.repository.service.ProviderIndustryInfoService;
import com.sj.repository.service.ProviderService;
import com.sj.repository.service.ProvinceService;
import com.sj.repository.service.SiteUserService;
import com.sj.repository.service.UserIndustryInfoService;
import com.sj.repository.util.ChangePasswordForm;
import com.sj.repository.util.MobileVerificationForm;
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
	private ProviderIndustryInfoService providerIndustryInfoService;
	@Autowired
	private CityService cityService;

	private final String LOGIN = "user/login";
	private final String COMMONSIGNUP = "user/common/signup";
	private final String PSIGNUP = "user/provider/signup";
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
			BindingResult bindingResult, Model uiModel, HttpSession httpSession) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				user.getName(), user.getPassword());
		try {
			authenticationManager.authenticate(token);
		} catch (BadCredentialsException exception) {
			user.setPassword(null);
			bindingResult.addError(new FieldError("user", "password", "密码错误"));
			uiModel.addAttribute("user", user);
			return LOGIN;
		}
		user = (SiteUser) userDetailsService.loadUserByUsername(user.getName());
		httpSession.removeAttribute("cartLines");
		userContext.setCurrentUser(user);
		if ("ROLE_COMMONUSER".equals(user.getSiteAuthority())) {
			 Set<CartLine> lines = cartLineService.findByUser(user.getId());
			 httpSession.setAttribute("cartLines", lines);
		}
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
			@RequestParam(value = "password", required = true) String password,HttpSession httpSession) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				name, password);
		try {
			authenticationManager.authenticate(token);
		} catch (BadCredentialsException exception) {
			return "fail";
		}
		SiteUser user = (SiteUser) userDetailsService.loadUserByUsername(name);
		userContext.setCurrentUser(user);
		if ("ROLE_COMMONUSER".equals(user.getSiteAuthority())) {
			 Set<CartLine> lines = cartLineService.findByUser(user.getId());
			 httpSession.setAttribute("cartLines", lines);
		}
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
		String code = (String) session.getAttribute("uCode");
		if(code == null || !code.equals(user.getCaptcha())){
			userResult.addError(new FieldError("user", "captcha", captchaError));
		}
		if (userResult.hasErrors()) {
			user.setPassword(null);
			user.setCaptcha(null);
			uiModel.addAttribute("provinces", provinceService.findAll());
			uiModel.addAttribute("infos", userIndustryInfoService.findAll());
			if (user.getProvince() != null) {
				uiModel.addAttribute("citys",cityService.findByProvince(user.getProvince()));
			}
			uiModel.addAttribute("user", user);
			return COMMONSIGNUP;
		}
		user.setPassword(encoder.encodePassword(user.getPassword(), null));
		SiteUser siteUser = commonUserService.create(user);
		userContext.setCurrentUser(siteUser);
		session.setAttribute("cartLines", new HashSet<CartLine>());
		session.removeAttribute("uCode");
		return "redirect:/";
	}

	/* provider registered page */
	@RequestMapping(value = "/signup", method = RequestMethod.GET, params = "provider")
	public String providerSignupForm(Model uiModel) {
		uiModel.addAttribute("user", new Provider());
		uiModel.addAttribute("provinces", provinceService.findAll());
		uiModel.addAttribute("infos", providerIndustryInfoService.findAll());
		return PSIGNUP;
	}

	/* provider registered */
	@RequestMapping(value = "/signup", method = RequestMethod.POST, params = "provider")
	public String providerSignupProcess(
			@Valid @ModelAttribute("user") Provider provider,
			BindingResult providerResult, Model uiModel,HttpSession session) {
		String code = (String) session.getAttribute("pCode");
		if(code == null ||!code.equals(provider.getCaptcha())){
			providerResult.addError(new FieldError("user", "captcha", captchaError));
		}
		if (providerResult.hasErrors()) {
			provider.setPassword(null);
			uiModel.addAttribute("provinces", provinceService.findAll());
			uiModel.addAttribute("infos", providerIndustryInfoService.findAll());
			if (provider.getProvince() != null) {
				uiModel.addAttribute("citys",
						cityService.findByProvince(provider.getProvince()));
			}
			uiModel.addAttribute("user", provider);
			return PSIGNUP;
		}
		provider.setPassword(encoder.encodePassword(provider.getPassword(),null));
		provider = providerService.create(provider);
		userContext.setCurrentUser(provider);
		session.removeAttribute("pCode");
		return "redirect:/";
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
	@RequestMapping(value = "/users/name", method = RequestMethod.POST)
	@ResponseBody
	public String validateUserNameIsExiste(@RequestParam("name") String name) {
		SiteUser user = userService.findByName(name);
		if (user == null) {
			return "true";
		}
		return "false";
	}
	@RequestMapping(value = "/users/phone", method = RequestMethod.POST)
	@ResponseBody
	public String validateUserPhoneIsExists(@RequestParam("phone") String phone){
		SiteUser user = userService.findByPhone(phone);
		if(user==null)
			return "true";
		return "false";
	}

	/* user change password feature */
	@RequestMapping(value = {"/supplier/changePw","/provider/changePw"}, method = RequestMethod.GET)
	public String editProviderPassword(Model uiModel,
			@SecurityUser SiteUser user) {
		uiModel.addAttribute("form", new ChangePasswordForm());
		return "user/provider/changePassword";
	}

	@RequestMapping(value = "/user/changePw", method = RequestMethod.GET)
	public String editCommonPassword(Model uiModel, @SecurityUser SiteUser user) {
		uiModel.addAttribute("form", new ChangePasswordForm());
		return "user/common/changePassword";
	}

	@RequestMapping(value = {"/supplier/changePw","/provider/changePw"}, method = RequestMethod.POST)
	public String processProviderPassword(
			@Valid @ModelAttribute("form") ChangePasswordForm form,
			BindingResult result, Model uiModel, @SecurityUser SiteUser user) {
		if(StringUtils.equals(form.getOldPassword(), form.getNewPassword())){
			result.addError(new FieldError("ChangePasswordForm", "oldPassword","新旧密码不能一致"));
			form.setNewPassword(null);
			form.setOldPassword(null);
			uiModel.addAttribute("form", form);
			return "user/provider/changePassword";
		}
		ChangePasswordForm source = form;
		form = translatePassword(form);
		validateChangePassword(user, form, result);
		if (result.hasErrors()) {
			source.setNewPassword("");
			source.setOldPassword("");
			uiModel.addAttribute("form", source);
			return "user/provider/changePassword";
		}
		SiteUser u = userService.updatePassword(user.getId(),
				form.getNewPassword());
		userContext.setCurrentUser(u);
		if("ROLE_PROVIDER".equals(u.getSiteAuthority())){
			return "redirect:/provider/detail";
		}
		return "redirect:/supplier/detail";
	}

	@RequestMapping(value = "/user/changePw", method = RequestMethod.POST)
	public String processUserPassword(
			@Valid @ModelAttribute("form") ChangePasswordForm form,
			BindingResult result, Model uiModel, @SecurityUser SiteUser user) {
		if(StringUtils.equals(form.getOldPassword(), form.getNewPassword())){
			result.addError(new FieldError("ChangePasswordForm", "oldPassword","新旧密码不能一致"));
			form.setNewPassword(null);
			form.setOldPassword(null);
			uiModel.addAttribute("form", form);
			return "user/common/changePassword";
		}
		ChangePasswordForm source = form;
		form = translatePassword(form);

		validateChangePassword(user, form, result);
		if (result.hasErrors()) {
			source.setNewPassword(null);
			source.setOldPassword(null);
			uiModel.addAttribute("form", source);
			return "user/common/changePassword";
		}
		SiteUser u = userService.updatePassword(user.getId(),form.getNewPassword());
		userContext.setCurrentUser(u);
		return "redirect:/user/detail";
	}

	private ChangePasswordForm translatePassword(ChangePasswordForm form) {
		ChangePasswordForm temp = new ChangePasswordForm();
		temp.setOldPassword(encoder.encodePassword(form.getOldPassword(), null));
		temp.setNewPassword(encoder.encodePassword(form.getNewPassword(), null));
		temp.setConfirmPassword(encoder.encodePassword(form.getConfirmPassword(), null));
		return temp;
	}

	private void validateChangePassword(SiteUser user, ChangePasswordForm form,
			BindingResult result) {
		if (!StringUtils.equals(user.getPassword(), form.getOldPassword())) {
			result.addError(new FieldError("ChangePasswordForm", "oldPassword",
					oldPasswordError));
		}
	}

	/* forget password */
	@RequestMapping(value = "/forgetPw", method = RequestMethod.GET)
	public String forgetPw(Model uiModel) {
		uiModel.addAttribute("form", new MobileVerificationForm());
		return "user/forgetPw";
	}

	@RequestMapping(value = "/forgetPw", method = RequestMethod.POST)
	public String forgetPwVaildata(
			@Valid @ModelAttribute("form") MobileVerificationForm form,
			BindingResult result, Model uiModel, HttpSession session) {
		SiteUser user = userService.findByPhone(form.getPhone());
		if(user == null)
			result.addError(new FieldError("form", "phone", "该手机号码未注册"));
		String code = (String) session.getAttribute("fCode");
		if(code==null || !code.equals(form.getCaptcha()))
			result.addError(new FieldError("form", "captcha", "验证码错误"));
		if (result.hasErrors()) {
			form.setCaptcha(null);
			uiModel.addAttribute("form", form);
			return "user/forgetPw";
		}
		
		session.setAttribute("phone", form.getPhone());
		session.removeAttribute("fCode");
		uiModel.addAttribute("form", new RetrievePasswordForm(form.getPhone()));
		return "user/changePw";
	}

	@RequestMapping(value = "/forgetPw", method = RequestMethod.PUT)
	public String forgetPwProcess(
			@Valid @ModelAttribute("form") RetrievePasswordForm form,
			BindingResult bindingResult, Model uiModel, HttpSession session) {
		if (!StringUtils.equals(form.getPassword(), form.getConfirm()))
			bindingResult.addError(new FieldError("form", "confirm","两次输入的密码不一致"));
		if (bindingResult.hasErrors()) {
			form.setPassword(null);
			form.setConfirm(null);
			uiModel.addAttribute("form", form);
			return "user/changePw";
		}
		String phone = (String) session.getAttribute("phone");
		if (phone == null || "".equals(phone)) {
			return "redirect:/forgetPw";
		}
		SiteUser user = userService.findByPhone(phone);
		userService.updatePassword(user.getId(),
				encoder.encodePassword(form.getPassword(), null));
		return "redirect:/login";
	}

	private void sendCaptcha() {

	}

}
