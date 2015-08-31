package com.sj.admin.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.admin.security.SiteUserContext;
import com.sj.model.model.ProductCategory;
import com.sj.repository.model.AdvertisementCategoryJson;
import com.sj.repository.model.InformationCategoryJson;
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

	@RequestMapping(value = "/admin/category/{category}", method = RequestMethod.GET, params = "children")
	@ResponseBody
	public List<Category> findCategroyByParent(@PathVariable("category") Long id) {
		List<ProductCategory> pcs = productCategoryService
				.findByParent(new ProductCategory(id));
		List<Category> categories = pcs.stream()
				.map((r) -> new Category(r.getId(), r.getName()))
				.collect(Collectors.toList());
		return categories;
	}

	@RequestMapping(value = "/admin/category/{category}", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteCategory(@PathVariable("id") Long id) {
		productCategoryService.delete(id);
		return true;
	}

	@RequestMapping(value = "/admin/category", method = RequestMethod.POST)
	@ResponseBody
	public Category addCategory(
			@Valid @ModelAttribute("category") ProductCategory pc,
			BindingResult result) {
		pc.setCreatedBy(userContext.getCurrnetUser().getName());
		pc = productCategoryService.save(pc);
		return new Category(pc.getId(), pc.getName());
	}

	private class Category {
		private Long id;
		private String name;

		public Category(Long id, String name) {
			this.id = id;
			this.name = name;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
