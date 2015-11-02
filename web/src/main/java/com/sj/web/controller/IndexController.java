package com.sj.web.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.model.model.Brand;
import com.sj.model.model.Information;
import com.sj.model.model.ProductCategory;
import com.sj.model.model.ProductDisplay;
import com.sj.model.model.ScrollImage;
import com.sj.model.model.SubjectCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.CategoryJson;
import com.sj.repository.service.BrandService;
import com.sj.repository.service.InformationCategoryService;
import com.sj.repository.service.InformationService;
import com.sj.repository.service.ProductCategoryService;
import com.sj.repository.service.ProductDisplayService;
import com.sj.repository.service.ScrollImageService;
import com.sj.repository.service.SubjectCategoryService;
import com.sj.repository.service.SubjectService;


@Controller
public class IndexController {
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private InformationService informationService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private ScrollImageService scrollImageService;
	@Autowired
	private InformationCategoryService informationCategorySerivce;
	@Autowired
	private SubjectCategoryService subjectCategoryService;
	@Autowired
	private ProductDisplayService productDisplayService;
	
	@RequestMapping(value = { "/", "/index" },method = RequestMethod.GET)
	public String index(Model uiModel) {
		Map<String,List<CategoryJson>> maps = productCategoryService.findAllShowOnHead();
		uiModel.addAttribute("yqs", maps.get("1"));
		uiModel.addAttribute("sjs", maps.get("2"));
		uiModel.addAttribute("hcs", maps.get("3"));
		uiModel.addAttribute("fws", maps.get("4"));
		
		List<SubjectCategory> subjectCategories = subjectCategoryService.findByParent();
		subjectCategories = subjectCategoryService.findByShowOnIndex(subjectCategories);
		uiModel.addAttribute("subjectCategories", subjectCategories);
		
		List<Information> industryNews =informationService.findByCategoryAndShowOnIndex(informationCategorySerivce.findOne(7l));
		List<Information> newResults =informationService.findByCategoryAndShowOnIndex(informationCategorySerivce.findOne(8l));
		List<Information> vendorDynamics =informationService.findByCategoryAndShowOnIndex(informationCategorySerivce.findOne(9l));
		
		uiModel.addAttribute("industryNews", industryNews);
		uiModel.addAttribute("newResults", newResults);
		uiModel.addAttribute("vendorDynamics", vendorDynamics);
		
		List<Brand> brands = brandService.findByAcitvate(ActivateEnum.ACTIVATE, new PageRequest(0, 10));
		uiModel.addAttribute("brands", brands);
		List<ScrollImage> scrollImages = scrollImageService.findAll();
		uiModel.addAttribute("images", scrollImages);
		uiModel.addAttribute("pc", new ProductCategory(0l));
		
		List<ProductDisplay> displays = productDisplayService.findAll();
		uiModel.addAttribute("displays", displays);
		return "index";
	}
	@RequestMapping(value = "/head",method = RequestMethod.GET)
	public String head(){
		return "head/head";
	}
}