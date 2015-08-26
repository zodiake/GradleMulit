package com.sj.web.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.model.model.Product;
import com.sj.model.model.ProductCategory;
import com.sj.model.model.Subject;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.service.ProductCategoryService;
import com.sj.repository.service.SubjectService;
import com.sj.web.exception.SubjectNotFoundException;

@Controller
public class SubjectController {
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private ProductCategoryService productCategoryService;

	@RequestMapping(value = "/subjects", method = RequestMethod.GET)
	public String findSubjects(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size,
			Model uiModel) {
		Page<Subject> subjects = subjectService.findByActivated(
				new PageRequest(page - 1, size, Direction.DESC, "createdTime"),
				ActivateEnum.ACTIVATE);
		uiModel.addAttribute("subjects", subjects);
		uiModel.addAttribute("pc", productCategoryService.findOne(5l));
		return "subject/subjects";
	}

	@RequestMapping(value = "/subjects/{id}", method = RequestMethod.GET)
	public String findOne(@PathVariable("id") Long id, Model uiModel) {
		Subject subject = subjectService.findOne(id);
		if (subject == null)
			throw new SubjectNotFoundException();
		uiModel.addAttribute("subject", subject);
		List<Product> yqs = new ArrayList<Product>();
		List<Product> sjs = new ArrayList<Product>();
		List<Product> hcs = new ArrayList<Product>();
		List<Product> fws = new ArrayList<Product>();
		Iterator<Product> ps = subject.getProducts().iterator();
		while (ps.hasNext()) {
			Product product = ps.next();
			String name = product.getFirstCategory().getName();
			switch (name) {
			case "仪器":
				yqs.add(product);
				break;
			case "试剂":
				sjs.add(product);
				break;
			case "耗材":
				hcs.add(product);
				break;
			case "服务":
				fws.add(product);
				break;
			}
		}
		uiModel.addAttribute("yqs", yqs);
		uiModel.addAttribute("sjs", sjs);
		uiModel.addAttribute("hcs", hcs);
		uiModel.addAttribute("fws", fws);
		uiModel.addAttribute("pc", productCategoryService.findOne(5l));
		return "subject/subject";
	}
}
