package com.sj.web.controller;

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
import com.sj.model.model.SiteUser;
import com.sj.repository.service.ProviderService;
import com.sj.repository.service.SiteUserService;
import com.sj.web.exception.UserNotFoundException;

@Controller
public class AdminUserController extends BaseController {
	@Autowired
	private SiteUserService userService;

	@Autowired
	private ProviderService providerService;

	private final String USERDETAIL = "user/userDetail";

	@RequestMapping(value = "/users")
	public String userList(Model uiModel,
			@RequestParam(defaultValue = "1", value = "page") int page,
			@RequestParam(defaultValue = "15", value = "size") int size) {
		PageRequest pageRequest = new PageRequest(page - 1, size,
				Direction.ASC, "createdTime");
		Page<SiteUser> userList = userService.findAll(pageRequest);
		uiModel.addAttribute("lists", userList);
		uiModel.addAttribute("page", caculatePage(userList));
		return "index";
	}

	@RequestMapping(value = "/checkUsers", method = RequestMethod.POST)
	@ResponseBody
	public String checkUser(
			@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "state", required = true) int state,
			Model uiModel) {
		userService.updateEnabledById(id, state);
		return "success";
	}

	@RequestMapping(value = "/provider/{id}", method = RequestMethod.GET)
	public String view(Model uiModel, @PathVariable("id") int id) {
		Provider p = providerService.findOne(id);
		if (p == null)
			throw new UserNotFoundException();
		uiModel.addAttribute("provider", p);
		return USERDETAIL;
	}
}
