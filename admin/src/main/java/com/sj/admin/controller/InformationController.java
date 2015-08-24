package com.sj.admin.controller;

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
import com.sj.repository.service.InformationService;

@Controller
public class InformationController {
	@Autowired
	private InformationService informationService;

	@RequestMapping(value = "/admin/informations", method = RequestMethod.GET)
	public String findAll(Model uiModel,
			@RequestParam(defaultValue = "1", value = "page") int page,
			@RequestParam(defaultValue = "15", value = "size") int size) {
		Page<Information> infos = informationService.findAll(new PageRequest(
				page - 1, size, Direction.DESC, "showOnIndex"));
		uiModel.addAttribute("information", infos);
		return null;
	}

	@RequestMapping(value = "/admin/informations/{id}", method = RequestMethod.GET)
	public String findOne(Model uiModel, @PathVariable("id") Long id) {
		Information info = informationService.findOne(id);
		uiModel.addAttribute("information", info);
		return null;
	}

	@RequestMapping(value = "/admin/informations", method = RequestMethod.GET, params = "form")
	public String create(Model uiModel) {
		uiModel.addAttribute("information", new Information());
		return null;
	}

	@RequestMapping(value = "/admin/informations", method = RequestMethod.POST, params = "form")
	public String createProcess(
			@Valid @ModelAttribute("information") Information info,
			BindingResult bindingResult, Model uiModel) {
		info = informationService.save(info);
		return null;
	}

	@RequestMapping(value = "/admin/informations/{id}", method = RequestMethod.GET, params = "edit")
	public String edit(Model uiModel, @PathVariable("id") Long id) {
		Information info = informationService.findOne(id);
		uiModel.addAttribute("informaiton", info);
		return null;
	}

	@RequestMapping(value = "/admin/informations/{id}",method = RequestMethod.PUT,params = "edit")
	public String editProcess(Model uiModel,
			@Valid @ModelAttribute("information") Information info) {
		return null;
	}
	@RequestMapping(value = "/admin/informaitons/{id}",method = RequestMethod.PUT,params="status")
	@ResponseBody
	public String editActivate(@PathVariable("id")Long id,@RequestParam("status")String status){
		Information info = informationService.findOne(id);
		if(info==null)
			return "error";
		info.setActivate(ActivateEnum.valueOf(status));
		informationService.save(info);
		return "success";
	}
}