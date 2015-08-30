package com.sj.web.controller;

import java.io.Reader;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.BuyRecord;
import com.sj.model.model.CommonUser;
import com.sj.model.model.PreferProduct;
import com.sj.model.model.Product;
import com.sj.model.model.SiteUser;
import com.sj.repository.service.BuyRecordService;
import com.sj.repository.service.CityService;
import com.sj.repository.service.CommonUserService;
import com.sj.repository.service.PreferProductService;
import com.sj.repository.service.ProductService;
import com.sj.repository.service.ProvinceService;
import com.sj.repository.service.UserIndustryInfoService;
import com.sj.web.annotation.SecurityUser;
import com.sj.web.exception.BuyRecordNotFoundException;
import com.sj.web.exception.ProductNotFoundException;
import com.sj.web.security.SiteUserContext;

@Controller
public class CommonUserController {
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
	private SiteUserContext siteUserContext;

	@RequestMapping(value = "/user/detail", method = RequestMethod.GET)
	public String findOne(Model uiModel) {
		SiteUser siteUser = userContext.getCurrentUser();
		CommonUser commonUser = commonUserService.findOne(siteUser.getId());
		uiModel.addAttribute("user", commonUser);
		uiModel.addAttribute("provinces", provinceService.findAll());
		uiModel.addAttribute("industryInfos", userIndustryInfoService.findAll());
		uiModel.addAttribute("citys",
				cityService.findByProvince(commonUser.getProvince()));
		return "/user/common/detail";
	}

	@RequestMapping(value = "/user/detail", method = RequestMethod.PUT)
	public String updateOne(Model uiModel,
			@Valid @ModelAttribute("user") CommonUser commonUser,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("user", commonUser);
			uiModel.addAttribute("provinces", provinceService.findAll());
			uiModel.addAttribute("industryInfos",
					userIndustryInfoService.findAll());
			uiModel.addAttribute("citys",
					cityService.findByProvince(commonUser.getProvince()));
			return "/user/common/detail";
		}
		SiteUser siteUser = userContext.getCurrentUser();
		commonUser.setId(siteUser.getId());
		commonUser = commonUserService.update(commonUser);
		uiModel.addAttribute("user", commonUser);
		return "redirect:/user/detail";
	}

	@RequestMapping(value = "/user/collection", method = RequestMethod.GET)
	public String getCollectionByUser(Model uiModel,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size) {
		SiteUser user = userContext.getCurrentUser();
		Page<PreferProduct> pages = preferService.findByUser(user,
				new PageRequest(page - 1, size));
		uiModel.addAttribute("pages", pages);
		return "/user/common/collection";
	}

	@RequestMapping(value = "/user/collection", method = RequestMethod.POST)
	public String addCollection(@SecurityUser SiteUser user,
			@RequestParam("id") Long id) {
		Product p = productServise.findOne(id);
		if (p == null)
			throw new ProductNotFoundException();
		PreferProduct prefer = new PreferProduct(new CommonUser(user.getId()),
				p);
		preferService.save(prefer);
		return "success";
	}

	@RequestMapping(value = "/user/collection/{productId}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteCollection(Model uiModel,
			@PathVariable(value = "productId") Long productId) {
		SiteUser user = userContext.getCurrentUser();
		preferService.deleteByUserAndProduct(new CommonUser(user.getId()),
				new Product(productId));
		;
		return "success";
	}

	@RequestMapping(value = "/user/buyRecords", method = RequestMethod.POST)
	public String save(@ModelAttribute("buy") BuyRecord buyRecord, Model uiModel) {
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
	public String findOne(@PathVariable("id") Long id, Model uiModel,
			@SecurityUser SiteUser user) {
		BuyRecord buyRecord = buyRecordService.findOne(id,new CommonUser(user.getId()));
		if (buyRecord == null)
			throw new BuyRecordNotFoundException();
		uiModel.addAttribute("buy", buyRecord);
		return "user/common/pdf";
	}

	@RequestMapping(value = "/user/buyRecords/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteOne(@PathVariable("id") Long id) {
		buyRecordService.deleteOne(id);
		return "success";
	}

	@RequestMapping(value = "/user/buyRecords/{id}", method = RequestMethod.GET, params = "edit")
	public String update(@PathVariable("id") Long id, Model uiModel,
			@SecurityUser SiteUser user) {
		BuyRecord buyRecord = buyRecordService.findOne(id,
				new CommonUser(user.getId()));
		if (buyRecord == null)
			throw new BuyRecordNotFoundException();
		uiModel.addAttribute("buy", buyRecord);
		return "user/common/modifyBuy";
	}

	@RequestMapping(value = "/user/buyRecords/{id}", method = RequestMethod.PUT, params = "edit")
	public String updateProcess(
			@Valid @ModelAttribute("buy") BuyRecord buyRecord,
			BindingResult result, @PathVariable("id") Long id, Model uiModel,
			@SecurityUser SiteUser user) {
		buyRecord = buyRecordService.update(new CommonUser(user.getId()), buyRecord);
		uiModel.addAttribute("buy", buyRecord);
		return "redirect:/user/buyRecords/" + id+"?edit";
	}
}
