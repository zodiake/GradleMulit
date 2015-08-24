package com.sj.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.BuyRecord;
import com.sj.model.model.CommonUser;
import com.sj.model.model.SiteUser;
import com.sj.repository.service.BuyRecordService;
import com.sj.web.security.SiteUserContext;

@Controller
public class BuyRecordController {

	@Autowired
	private BuyRecordService buyRecordService;
	@Autowired
	private SiteUserContext siteUserContext;

	@RequestMapping(value = "/user/buyRecords", method = RequestMethod.POST)
	public String save(@ModelAttribute("buy") BuyRecord buyRecord, Model uiModel) {
		//
		BuyRecord buy = buyRecordService.save(buyRecord);
		uiModel.addAttribute("buy", buy);
		return null;
	}

	@RequestMapping(value = "/user/buyRecords", method = RequestMethod.GET)
	public String findAll(Model uiModel,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size) {
		SiteUser user = siteUserContext.getCurrentUser();
		Page<BuyRecord> pages = buyRecordService.findPage(
				new CommonUser(user.getId()), new PageRequest(page - 1, size));
		uiModel.addAttribute("pages", pages);
		return "user/common/buys";
	}

	@RequestMapping(value = "/user/buyRecords/{id}", method = RequestMethod.GET)
	public String findOne(@PathVariable("id")Long id,Model uiModel) {
		BuyRecord buyRecord = buyRecordService.findOne(id);
		uiModel.addAttribute("buy", buyRecord);
		return "user/common/pdf";
	}
	@RequestMapping(value = "/user/buyRecords/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteOne(@PathVariable("id")Long id){
		buyRecordService.deleteOne(id);
		return "success";
	}
}
