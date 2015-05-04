package com.sj.admin.controller;

import java.util.Set;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.Product;
import com.sj.model.model.ProductSubject;
import com.sj.model.model.Subject;
import com.sj.repository.service.ProductSubjectService;
import com.sj.repository.service.SubjectService;

@Controller
public class ProductSubjectController {
	@Autowired
	private ProductSubjectService service;
	@Autowired
	private SubjectService subjectService;

	private final String LIST = "productSubject/list";

	@RequestMapping(value = "/admin/subjects/{subjectId}/products", method = RequestMethod.GET)
	public String view(@PathVariable("subjectId") Long subjectId,
			Model uiModel,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size) {
		Page<ProductSubject> lists = service.findBySubject(new Subject(
				subjectId), new PageRequest(page - 1, size, Direction.DESC,
				"dateAdded"));
		Set<Product> products = lists.getContent().stream()
				.map(ps -> ps.getProduct()).collect(Collectors.toSet());
		uiModel.addAttribute("lists", products);
		return LIST;
	}

	@RequestMapping(value = "/productSubject/list", method = RequestMethod.POST)
	@ResponseBody
	public String list(@RequestParam(value = "param[]") Integer[] params) {
		return params.toString();
	}
}