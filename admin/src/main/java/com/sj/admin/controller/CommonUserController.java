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

import com.sj.admin.exception.UserNotFoundException;
import com.sj.model.model.CommonUser;
import com.sj.repository.model.CommonUserDetailJson;
import com.sj.repository.model.CommonUserJson;
import com.sj.repository.service.CommonUserService;

@Controller
public class CommonUserController {
	@Autowired
	private CommonUserService userService;

	@RequestMapping(value = "/admin/CommonUsers", method = RequestMethod.GET)
	@ResponseBody
	public Page<CommonUserJson> findAllDesc(Model uiModel,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size) {
		Page<CommonUserJson> users = userService.toJson(new PageRequest(
				page - 1, size, Direction.DESC, "createTime"));
		return users;
	}

	@RequestMapping(value = "/admin/CommonUsers/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonUserDetailJson findOne(Model uiModel,
			@PathVariable("id") Long id) {
		CommonUser user = userService.findOne(id);
		if (user == null)
			throw new UserNotFoundException();
		return new CommonUserDetailJson(user);
	}

	@RequestMapping(value = "/admin/CommonUsers/{id}/score", method = RequestMethod.POST)
	@ResponseBody
	public String updateScore(HttpServletRequest request,
			@PathVariable(value = "id") Long id) {
		int score = Integer.parseInt(request.getParameter("score"));
		userService.updateScore(id, score);
		return "\"success\"";
	}
}
