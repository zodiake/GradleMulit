package com.sj.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.model.model.CommonUser;
import com.sj.model.model.SiteUser;
import com.sj.repository.service.BuyProductService;
import com.sj.repository.service.BuyRecordService;
import com.sj.repository.service.CartLineService;
import com.sj.repository.service.CityService;
import com.sj.repository.service.CommonUserService;
import com.sj.repository.service.PreferProductService;
import com.sj.repository.service.ProductService;
import com.sj.repository.service.ProvinceService;
import com.sj.repository.service.UserIndustryInfoService;
import com.sj.web.security.SiteUserContext;

@Controller
public class CommonUserController{
	@Autowired
	private SiteUserContext userContext;
	@Autowired
	private CommonUserService commonUserService;
	@Autowired
	private PreferProductService preferService;
	@Autowired
	private ProductService productServise;
	@Autowired
	private UserIndustryInfoService userIndustryInfoService;
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private CityService cityService;
	@Autowired
	private BuyRecordService buyRecordService;
	@Autowired
	private BuyProductService buyProductService;
	@Autowired
	private SiteUserContext siteUserContext;
	@Autowired
	private CartLineService cartLineService;

	@RequestMapping(value = "/user/detail", method = RequestMethod.GET)
	public String findOne(Model uiModel) {
		SiteUser siteUser = userContext.getCurrentUser();
		CommonUser commonUser = commonUserService.findOne(siteUser.getId());
		uiModel.addAttribute("user", commonUser);
		uiModel.addAttribute("provinces", provinceService.findAll());
		uiModel.addAttribute("industryInfos", userIndustryInfoService.findAll());
		uiModel.addAttribute("citys",cityService.findByProvince(commonUser.getProvince()));
		return "user/common/detail";
	}

	@RequestMapping(value = "/user/detail", method = RequestMethod.PUT)
	public String updateOne(Model uiModel,
			@Valid @ModelAttribute("user") CommonUser commonUser,
			BindingResult bindingResult) {
		System.out.println(commonUser.getSex().toString());
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("user", commonUser);
			uiModel.addAttribute("provinces", provinceService.findAll());
			uiModel.addAttribute("industryInfos",userIndustryInfoService.findAll());
			uiModel.addAttribute("citys",cityService.findByProvince(commonUser.getProvince()));
			return "user/common/detail";
		}
		SiteUser siteUser = userContext.getCurrentUser();
		commonUser.setId(siteUser.getId());
		commonUser = commonUserService.update(commonUser);
//		uiModel.addAttribute("user", commonUser);
		uiModel.addAttribute("href", "/user/detail");
		return "user/update-result";
	}

}
