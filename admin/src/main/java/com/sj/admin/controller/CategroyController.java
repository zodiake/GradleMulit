package com.sj.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.ProductCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.AdvertisementCategoryJson;
import com.sj.repository.model.InformationCategoryJson;
import com.sj.repository.model.ProductCategoryDetailJson;
import com.sj.repository.model.ProductCategoryJson;
import com.sj.repository.model.SubjectCategoryJson;
import com.sj.repository.service.AdvertisementCategoryService;
import com.sj.repository.service.InformationCategoryService;
import com.sj.repository.service.ProductCategoryService;
import com.sj.repository.service.SubjectCategoryService;

@Controller
public class CategroyController {
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private AdvertisementCategoryService advertisementCategoryService;
	@Autowired
	private InformationCategoryService informationCategoryService;
	@Autowired
	private SubjectCategoryService subjectCategoryService;

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

	@RequestMapping(value = "/admin/subject/category", method = RequestMethod.GET)
	@ResponseBody
	public List<SubjectCategoryJson> findSubjectCategory() {
		return subjectCategoryService.findAllJson();
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

	@RequestMapping(value = "/admin/product/category/{id}/categories", method = RequestMethod.POST)
	@ResponseBody
	public String addProductCategory(@PathVariable("id") Long id,
			ProductCategory category) {
		ProductCategory pc = productCategoryService.save(category);
		return "{\"id\":\"" + pc.getId() + "\"}";
	}

	@RequestMapping(value = "/admin/product/category/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String updateProductCategory(@PathVariable("id") Long id,
			ProductCategory pc) {
		pc.setId(id);
		productCategoryService.update(pc);
		return "{\"id\":\"" + id + "\"}";
	}

	@RequestMapping(value = "/admin/product/categories/{id}/state", method = RequestMethod.POST)
	@ResponseBody
	public String updateProductCategoryState(@PathVariable("id") Long id,
			HttpServletRequest request) {
		ActivateEnum activate = ActivateEnum.fromString(request
				.getParameter("activate"));
		productCategoryService.updateState(id, activate);
		return "\"success\"";
	}

}
