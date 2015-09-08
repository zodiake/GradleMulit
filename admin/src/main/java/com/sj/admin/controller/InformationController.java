package com.sj.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.Information;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.InformationDetailJson;
import com.sj.repository.model.InformationJson;
import com.sj.repository.service.InformationService;

@Controller
public class InformationController {
	@Autowired
	private InformationService informationService;

	@RequestMapping(value = "/admin/information", method = RequestMethod.GET)
	@ResponseBody
	public Page<InformationJson> findAll(Model uiModel,
			@RequestParam(required = false, value = "state") String state,
			@RequestParam(defaultValue = "1", value = "page") int page,
			@RequestParam(defaultValue = "15", value = "size") int size) {
		ActivateEnum activate = ActivateEnum.fromString(state);
		return informationService.findAllJson(new PageRequest(page - 1, size,
				Direction.DESC, "createdTime"), activate);
	}

	@RequestMapping(value = "/admin/info/{id}/state", method = RequestMethod.POST)
	@ResponseBody
	public String updateState(@PathVariable("id") Long id,
			HttpServletRequest request) {
		ActivateEnum state = ActivateEnum.fromString(request
				.getParameter("state"));
		informationService.updateState(id, state);
		return "\"success\"";
	}

	@RequestMapping(value = "/admin/informations/{id}", method = RequestMethod.GET)
	@ResponseBody
	public InformationJson findOne(Model uiModel, @PathVariable("id") Long id) {
		Information info = informationService.findOne(id);
		return new InformationDetailJson(info);
	}

	@RequestMapping(value = "/admin/informations", method = RequestMethod.POST)
	@ResponseBody
	public String createProcess(
			@Valid @ModelAttribute("information") Information info,
			BindingResult bindingResult, Model uiModel) {
		info.setActivate(ActivateEnum.ACTIVATE);
		if (bindingResult.hasErrors()) {
			return "\"fail\"";
		}
		info = informationService.save(info);
		return "\"success\"";
	}

	@RequestMapping(value = "/admin/informations/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String update(@PathVariable("id") Long id, Information information) {
		information.setId(id);
		informationService.update(information);
		return "";
	}

}
