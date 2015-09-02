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

import com.sj.admin.exception.AdvertisementNotFoundException;
import com.sj.model.model.Advertisement;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.AdvertisementJson;
import com.sj.repository.service.AdvertisementService;

@Controller
public class AdvertisementController {
	@Autowired
	private AdvertisementService advertisementService;

	@RequestMapping(value = "/admin/advertisements", method = RequestMethod.POST)
	@ResponseBody
	public String createProcess(
			@Valid @ModelAttribute("advertisement") Advertisement advertisement,
			BindingResult bindingResult, Model uiModel) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("advertisement", advertisement);
			return "\"fail\"";
		}
		advertisement.setActivate(ActivateEnum.ACTIVATE);
		Advertisement adv = advertisementService.save(advertisement);
		System.out.println(adv.getActivate());
		return "\"success\"";
	}

	@RequestMapping(value = "/admin/advertisements/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String update(@PathVariable("id") Long id,
			Advertisement advertisement) {
		advertisement.setId(id);
		advertisementService.update(advertisement);
		return "\"success\"";
	}

	@RequestMapping(value = "/admin/advertisements", method = RequestMethod.GET)
	@ResponseBody
	public Page<AdvertisementJson> findAll(Model uiModel,
			@RequestParam(defaultValue = "1", value = "page") int page,
			@RequestParam(defaultValue = "15", value = "size") int size,
			@RequestParam(defaultValue = "all", value = "state") String state) {
		ActivateEnum activate = ActivateEnum.fromString(state);
		return advertisementService.findAllJson(new PageRequest(page - 1, size,
				Direction.DESC, "activate"), activate);
	}

	@RequestMapping(value = "/admin/advertisements/{status}", method = RequestMethod.GET, params = "status")
	public String Search(Model uiModel,
			@RequestParam(defaultValue = "1", value = "page") int page,
			@RequestParam(defaultValue = "15", value = "size") int size,
			@PathVariable("status") String status) {
		Page<Advertisement> advs = advertisementService.findByActivate(
				new PageRequest(page - 1, size), ActivateEnum.valueOf(status));
		uiModel.addAttribute("advertisements", advs);
		return null;
	}

	@RequestMapping(value = "/admin/advertisements/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model uiModel) {
		Advertisement adv = advertisementService.findOne(id);
		if (adv == null)
			throw new AdvertisementNotFoundException();
		uiModel.addAttribute("advertisement", adv);
		return null;
	}

	@RequestMapping(value = "/admin/advertisements/{id}", method = RequestMethod.PUT, params = "status")
	@ResponseBody
	public String updateStatus(@PathVariable("id") Long id,
			@RequestParam("status") int status) {
		Advertisement adv = advertisementService.findOne(id);
		if (adv == null)
			return "error";
		adv.setActivate(ActivateEnum.values()[status]);
		advertisementService.updateStatus(adv);
		return "success";
	}
}
