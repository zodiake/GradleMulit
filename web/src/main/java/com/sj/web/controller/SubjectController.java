package com.sj.web.controller;

import static com.sj.repository.util.RedisConstant.REVIEWCOUNT;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.Subject;
import com.sj.model.model.SubjectCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.SubjectListJson;
import com.sj.repository.service.ProductService;
import com.sj.repository.service.SolutionService;
import com.sj.repository.service.SubjectCategoryService;
import com.sj.repository.service.SubjectService;
import com.sj.web.exception.CategoryNotFoundException;
import com.sj.web.exception.SubjectNotFoundException;

@Controller
public class SubjectController extends BaseController<Subject> {
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private SubjectCategoryService subjectCategoryService;
	@Autowired
	private StringRedisTemplate template;
	@Autowired
	private SolutionService solutionService;
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/subjectCategorys/{id}", method = RequestMethod.GET)
	public String findSubjects(@PathVariable("id") Long id,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "12") int size,
			Model uiModel) {
		SubjectCategory category = subjectCategoryService.findOne(id);
		if(category==null)
			throw new CategoryNotFoundException();
		Page<Subject> subjects = subjectService.findByCategoryAndActivate(
				category, new PageRequest(page, size), ActivateEnum.ACTIVATE);

		ViewPage viewpage = caculatePage(subjects);
		viewpage.setHref("/subjects");
		viewpage.setCurrent(subjects.getNumber());

		uiModel.addAttribute("subjects", subjects);
		uiModel.addAttribute("viewpage", viewpage);
		uiModel.addAttribute("pc", new SubjectCategory(6l));
		return "subject/subjects";
	}

	@RequestMapping(value = "/subjects/{id}", method = RequestMethod.GET)
	public String findOne(@PathVariable("id") Long id, Model uiModel) {
		Subject subject = subjectService.findOne(id);
		if (subject == null)
			throw new SubjectNotFoundException();

		Long subjectCount = template.opsForValue().increment(REVIEWCOUNT + id,
				1);
		subject.setViewCount(subjectCount);
		uiModel.addAttribute("subject", subject);
		uiModel.addAttribute("pc", new SubjectCategory(6l));
		return "subject/subject";
	}

	@RequestMapping(value = "/products/{productId}/subjects", method = RequestMethod.GET)
	@ResponseBody
	public Set<SubjectListJson> findSubjectByProduct(
			@PathVariable("productId") Long productId) {
		Set<SubjectListJson> subjects = subjectService.findByProduct(productId);
		return subjects;
	}
}
