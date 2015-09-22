package com.sj.admin.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.admin.exception.GlobalException;
import com.sj.model.model.SiteMenu;
import com.sj.model.model.SiteRole;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.SiteRoleJson;
import com.sj.repository.service.SiteRoleService;

@Controller
@RequestMapping("/admin")
public class AuthorityController {
	@Autowired
	private SiteRoleService service;

	@RequestMapping(value = "/authorities", method = RequestMethod.GET)
	@ResponseBody
	public List<SiteRoleJson> list(HttpServletRequest request) {
		String active = request.getParameter("active");
		ActivateEnum activeEnum = ActivateEnum.fromString(active);
		return service.findAllJson(activeEnum);
	}

	@RequestMapping(value = "/authorities", method = RequestMethod.POST)
	@ResponseBody
	public String save(@RequestParam("menus") String menus,
			@RequestParam("name") String name) {
		if (StringUtils.isEmpty(name)) {
			throw new GlobalException();
		}
		SiteRole role = new SiteRole();
		role.setRoleName(name);
		role.setMenus(convertStringToSiteRole(menus));
		SiteRole r = service.save(role);
		return "{\"id\":\"" + r.getId() + "\"}";
	}

	@RequestMapping(value = "/authorities/{id}", method = RequestMethod.GET)
	@ResponseBody
	public SiteRoleJson findOne(@PathVariable("id") Long id) {
		return service.findOneJson(id);
	}

	@RequestMapping(value = "/authorities/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String update(@PathVariable("id") Long id,
			@RequestParam("menus") String menus,
			@RequestParam("name") String name) {
		SiteRole role = new SiteRole(id);
		role.setRoleName(name);
		role.setMenus(convertStringToSiteRole(menus));
		service.update(role);
		return "{\"id\":\"" + id + "\"}";
	}

	private List<SiteMenu> convertStringToSiteRole(String siteRoles) {
		String[] array = siteRoles.split(",");
		return Arrays.stream(array).map(r -> new SiteMenu(Long.parseLong(r)))
				.collect(Collectors.toList());
	}

	@RequestMapping(value = "/authorities/{id}/state", method = RequestMethod.POST)
	@ResponseBody
	public String updateState(HttpServletRequest request,
			@PathVariable("id") Long id) {
		String active = request.getParameter("active");
		ActivateEnum activeEnum = ActivateEnum.fromString(active);
		service.updateState(id, activeEnum);
		return "{\"status\":\"success\"}";
	}
}
