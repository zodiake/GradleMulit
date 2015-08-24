package com.sj.web.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sj.model.model.Information;
import com.sj.model.model.InformationCategory;
import com.sj.model.model.ProductCategory;
import com.sj.model.model.Subject;
import com.sj.repository.service.InformationService;
import com.sj.repository.service.ProductCategoryService;
import com.sj.repository.service.SubjectService;


@Controller
public class IndexController {
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private InformationService informationService;
	@Autowired
	private SubjectService subjectService;
	
	@RequestMapping(value = { "/", "/index" })
	public String index(Model uiModel) {
		//商品分类
		List<ProductCategory> yqs = productCategoryService.findByParent(new ProductCategory(1l));
		uiModel.addAttribute("yqs", yqs);
		List<ProductCategory> sjs = productCategoryService.findByParent(new ProductCategory(2l));
		uiModel.addAttribute("sjs", sjs);
		List<ProductCategory> hcs = productCategoryService.findByParent(new ProductCategory(3l));
		uiModel.addAttribute("hcs", hcs);
		List<ProductCategory> fws = productCategoryService.findByParent(new ProductCategory(4l));
		uiModel.addAttribute("fws", fws);
		uiModel.addAttribute("pc", new ProductCategory());
		
		List<Subject> subjects = subjectService.findByShowOnIndex();
		uiModel.addAttribute("subjects", subjects);
		
		List<Information> isH = informationService.findByCategoryAndShowOnIndex(InformationCategory.getFirst(InformationCategory.HYYW));
		List<Information> isX = informationService.findByCategoryAndShowOnIndex(InformationCategory.getFirst(InformationCategory.XPCG));
		List<Information> isC = informationService.findByCategoryAndShowOnIndex(InformationCategory.getFirst(InformationCategory.CSDT));
		uiModel.addAttribute("isH", isH);
		uiModel.addAttribute("isX", isX);
		uiModel.addAttribute("isC", isC);
		return "index";
	}
}