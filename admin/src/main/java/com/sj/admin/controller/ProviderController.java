package com.sj.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.Provider;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.ProviderJson;
import com.sj.repository.service.ProviderService;

@Controller
public class ProviderController {
	private final String PROVIDER = "provider";
	@Autowired
	private ProviderService providerService;

	@RequestMapping(value = "/admin/providers", method = RequestMethod.GET)
	@ResponseBody
	public Page<ProviderJson> findAllDesc(HttpServletRequest request,
			Model uiModel,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size) {
		String state = request.getParameter("state");
		ActivateEnum stateEnum = ActivateEnum.fromString(state);
		Page<ProviderJson> providers = providerService.toJson(new PageRequest(
				page - 1, size, Direction.DESC, "createTime"), stateEnum);
		return providers;
	}

	@RequestMapping(value = "/admin/providers/{status}", method = RequestMethod.GET)
	public String findByStatus(Model uiModel,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size,
			@PathVariable("status") int status) {
		Page<Provider> providers = providerService.findAllDescAndStatus(
				new PageRequest(page - 1, size, Direction.DESC, "createTime"),
				ActivateEnum.values()[status]);
		uiModel.addAttribute("providers", providers);
		return null;
	}

	@RequestMapping(value = "/admin/providers/{id}", method = RequestMethod.GET)
	public String findOne(Model uiModel, @PathVariable("id") Long id) {
		Provider provider = providerService.findOne(id);
		uiModel.addAttribute("provider", provider);
		return null;
	}

	@RequestMapping(value = "/admin/providers/{id}", method = RequestMethod.PUT)
	public String checkUser(@PathVariable("id") Long id) {
		Provider provider = providerService.findOne(id);
		provider = providerService.checkUser(provider, ActivateEnum.ACTIVATE);// 修改
		return null;
	}
}
