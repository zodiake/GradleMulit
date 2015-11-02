package com.sj.web.controller;

import static com.sj.repository.util.RedisConstant.REVIEWCOUNT;

import java.util.List;
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

	@RequestMapping(value = "/subjects", method = RequestMethod.GET)
	public String findSubjects(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "12") int size,
			Model uiModel) {

		List<SubjectCategory> subjectCategories = subjectCategoryService.findByParent();

		Page<Subject> subjects = subjectService.findByCategoryAndActivate(new SubjectCategory(6l),
				new PageRequest(page, size), ActivateEnum.ACTIVATE);

		ViewPage viewpage = caculatePage(subjects);
		viewpage.setHref("/subjects");
		viewpage.setCurrent(subjects.getNumber());

		uiModel.addAttribute("subjects", subjects);
		uiModel.addAttribute("viewpage", viewpage);
		SubjectCategory sc = new SubjectCategory(6l);
		sc.setName("解决方案");
		uiModel.addAttribute("pc", sc);
		uiModel.addAttribute("currentSubjectCategory", new SubjectCategory(6l));
		uiModel.addAttribute("subjectCategories", subjectCategories);
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

	@RequestMapping(value = "/subjectCategories/{id}", method = RequestMethod.GET)
	public String findByCategory(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "12") int size,
			@PathVariable("id") Long id, Model uiModel) {
		SubjectCategory sc = subjectCategoryService.findOne(id);
		if(sc==null)
			throw new CategoryNotFoundException();
		List<SubjectCategory> subjectCategories = subjectCategoryService.findByParent();

		Page<Subject> subjects = subjectService.findByCategoryAndActivate(sc,
				new PageRequest(page, size), ActivateEnum.ACTIVATE);
		
		ViewPage viewpage = caculatePage(subjects);
		viewpage.setHref("/subjects");
		viewpage.setCurrent(subjects.getNumber());

		uiModel.addAttribute("subjects", subjects);
		uiModel.addAttribute("viewpage", viewpage);
		uiModel.addAttribute("pc", new SubjectCategory(6l));
		uiModel.addAttribute("currentSubjectCategory", sc);
		uiModel.addAttribute("subjectCategories", subjectCategories);
		return "subject/subjects";
	}
}
