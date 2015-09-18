package com.sj.web.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.model.model.Brand;
import com.sj.model.model.Information;
import com.sj.model.model.ProductCategory;
import com.sj.model.model.ScrollImage;
import com.sj.model.model.Subject;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.service.BrandService;
import com.sj.repository.service.InformationCategoryService;
import com.sj.repository.service.InformationService;
import com.sj.repository.service.ProductCategoryService;
import com.sj.repository.service.ScrollImageService;
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
	
	@RequestMapping(value = { "/", "/index" },method = RequestMethod.GET)
	public String index(Model uiModel) {
		List<ProductCategory> yqs = productCategoryService.findSecondCategory(new ProductCategory(1l),new PageRequest(0, 9));
		uiModel.addAttribute("yqs", yqs);
		List<ProductCategory> sjs = productCategoryService.findSecondCategory(new ProductCategory(2l),new PageRequest(0, 8));
		uiModel.addAttribute("sjs", sjs);
		List<ProductCategory> hcs = productCategoryService.findSecondCategory(new ProductCategory(3l),new PageRequest(0, 12));
		uiModel.addAttribute("hcs", hcs);
		List<ProductCategory> fws = productCategoryService.findSecondCategory(new ProductCategory(4l),new PageRequest(0, 14));
		uiModel.addAttribute("fws", fws);
		uiModel.addAttribute("pc", new ProductCategory());
		
		List<Subject> subjects = subjectService.findByShowOnIndex();
		uiModel.addAttribute("subjects", subjects);
		List<Information> industryNews =informationService.findByCategoryAndShowOnIndex(informationCategorySerivce.findOne(7l));
		List<Information> newResults =informationService.findByCategoryAndShowOnIndex(informationCategorySerivce.findOne(8l));
		List<Information> vendorDynamics =informationService.findByCategoryAndShowOnIndex(informationCategorySerivce.findOne(9l));
		
		uiModel.addAttribute("industryNews", industryNews);
		uiModel.addAttribute("newResults", newResults);
		uiModel.addAttribute("vendorDynamics", vendorDynamics);
		
		List<Brand> brands = brandService.findByAcitvate(ActivateEnum.ACTIVATE, new PageRequest(0, 5));
		uiModel.addAttribute("brands", brands);
		List<ScrollImage> scrollImages = scrollImageService.findAll();
		uiModel.addAttribute("images", scrollImages);
		uiModel.addAttribute("pc", new ProductCategory(0l));
		return "index";
	}
	@RequestMapping(value = "/head",method = RequestMethod.GET)
	public String head(){
		return "head/head";
	}
}