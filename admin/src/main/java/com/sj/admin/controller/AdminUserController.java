package com.sj.admin.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.SiteRole;
import com.sj.model.model.SiteUser;
import com.sj.repository.model.SiteUserDetailJson;
import com.sj.repository.model.SiteUserJson;
import com.sj.repository.service.ProviderService;
import com.sj.repository.service.SiteUserService;

@Controller
public class AdminUserController {
	@Autowired
	private SiteUserService userService;
	@Autowired
	private ProviderService providerService;
	@Autowired
	private ShaPasswordEncoder encoder;

	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	@ResponseBody
	public Page<SiteUserJson> userList(
			@RequestParam(defaultValue = "1", value = "page") int page,
			@RequestParam(defaultValue = "15", value = "size") int size) {
		Page<SiteUserJson> pages = userService.findBySiteAuthority(
				"ROLE_ADMIN", new PageRequest(page - 1, size));
		return pages;
	}

	@RequestMapping(value = "/admin/users/{id}", method = RequestMethod.GET)
	@ResponseBody
	public SiteUserDetailJson findOne(@PathVariable("id") Long id) {
		return userService.findOneJson(id);
	}

	@RequestMapping(value = "/admin/users/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String update(@PathVariable("id") Long id, SiteUser user,
			HttpServletRequest request) {
		String roles = request.getParameter("roles");
		user.setRoles(converteStringToSiteRoles(roles));
		SiteUser u = userService.update(user);
		return "{\"id\":\"" + u.getId() + "\"}";
	}

	@RequestMapping(value = "/admin/users", method = RequestMethod.POST)
	@ResponseBody
	public String save(SiteUser user, HttpServletRequest request) {
		String roles = request.getParameter("roles");
		user.setRoles(converteStringToSiteRoles(roles));
		user.setPassword(encoder.encodePassword(user.getPassword(), null));
		SiteUser u = userService.save(user);
		return "{\"id\":\"" + u.getId() + "\"}";
	}

	private List<SiteRole> converteStringToSiteRoles(String sRoles) {
		if (StringUtils.isNotEmpty(sRoles)) {
			return Arrays.stream(sRoles.split(","))
					.map(r -> new SiteRole(Long.parseLong(r)))
					.collect(Collectors.toList());
		}
		return null;
	}

	@RequestMapping(value = "/checkUsers", method = RequestMethod.POST)
	@ResponseBody
	public String checkUser(
			@RequestParam(value = "id", required = true) Long id,
			@RequestParam(value = "state", required = true) int state,
			Model uiModel) {
		userService.updateEnabledById(id, state);
		return "success";
	}
}