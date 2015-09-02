package com.sj.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.admin.security.SiteUserContext;
import com.sj.model.model.ProductCategory;
import com.sj.repository.model.AdvertisementCategoryJson;
import com.sj.repository.model.InformationCategoryJson;
import com.sj.repository.model.ProductCategoryDetailJson;
import com.sj.repository.model.ProductCategoryJson;
import com.sj.repository.service.AdvertisementCategoryService;
import com.sj.repository.service.InformationCategoryService;
import com.sj.repository.service.ProductCategoryService;

@Controller
public class CategroyController {
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private AdvertisementCategoryService advertisementCategoryService;
	@Autowired
	private InformationCategoryService informationCategoryService;
	@Autowired
	private SiteUserContext userContext;

	@RequestMapping(value = "/admin/advertise/category", method = RequestMethod.GET)
	@ResponseBody
	public List<AdvertisementCategoryJson> findAdvertiseCategory() {
		return advertisementCategoryService.findAllJson();
	}

	@RequestMapping(value = "/admin/info/category", method = RequestMethod.GET)
	@ResponseBody
	public Page<InformationCategoryJson> findInfoCategory() {
		return informationCategoryService.findAllJson();
	}

	@RequestMapping(value = "/admin/product/category", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductCategoryJson> findProductCategory() {
		return productCategoryService.findByParentJson(null);
	}

	@RequestMapping(value = "/admin/product/category/{id}/categories", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductCategoryJson> findSecondCategory(
			@PathVariable("id") Long id) {
		ProductCategory c = new ProductCategory(id);
		return productCategoryService.findByParentJson(c);
	}

	@RequestMapping(value = "/admin/product/categories", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductCategoryDetailJson> findProductCategoryAndChildren() {
		return productCategoryService.findAllDetail();
	}
}
