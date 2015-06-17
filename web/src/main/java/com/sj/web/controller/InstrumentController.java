package com.sj.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.model.model.Brand;
import com.sj.model.model.Instrument;
import com.sj.model.model.ProductCategory;
import com.sj.model.model.Provider;
import com.sj.model.model.Review;
import com.sj.model.model.SiteUser;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.service.BrandService;
import com.sj.repository.service.InstrumentService;
import com.sj.repository.service.ProductCategoryService;
import com.sj.repository.service.ReviewService;
import com.sj.web.exception.NoAuthorityException;
import com.sj.web.security.UserContext;

@Controller
public class InstrumentController {
	@Autowired
	private InstrumentService instrumentService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private UserContext userContext;
	@Autowired
	private BrandService brandService;
	@Autowired
	private ProductCategoryService categoryService;

	private final String CREATE = "instrument/create";
	private final String EDIT = "instrument/edit";
	private final String VIEW = "instrument/view";
	private final String LIST = "instrument/list";

	@RequestMapping(value = "/provider/instruments", params = "form", method = RequestMethod.GET)
	public String craete(Model uiModel) {
		Instrument instrument = new Instrument();
		List<Brand> brands = brandService.findByAcitvate(ActivateEnum.ACTIVATE);
		List<ProductCategory> categories = categoryService
				.findAllSecondCategory(ActivateEnum.ACTIVATE);
		uiModel.addAttribute("instrument", instrument);
		uiModel.addAttribute("brands", brands);
		uiModel.addAttribute("categories", categories);
		return CREATE;
	}

	@RequestMapping(value = "/provider/instruments", params = "form", method = RequestMethod.POST)
	public String createInstrument(Model uiModel,
			@Valid @ModelAttribute("instrument") Instrument instrument,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.toString());
			uiModel.addAttribute("instrument", instrument);
			return CREATE;
		}
		Provider p = new Provider();
		p.setId(userContext.getCurrentUser().getId());
		instrument.setCreatedBy(p);
		Instrument result = instrumentService.save(instrument);
		return "redirect:/provider/instruments/" + result.getId() + "?edit";
	}

	@RequestMapping(value = "/provider/instruments/{id}", params = "edit", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model uiModel) {
		Instrument instrument = instrumentService.findOne(id);
		List<Brand> brands = brandService.findByAcitvate(ActivateEnum.ACTIVATE);
		List<ProductCategory> categories = categoryService
				.findAllSecondCategory(ActivateEnum.ACTIVATE);
		List<ProductCategory> thirdCategories = categoryService
				.findByParentAndActivate(instrument.getSecondCategory(),
						ActivateEnum.ACTIVATE);
		SiteUser user = userContext.getCurrentUser();
		if (instrument.getCreatedBy().getId() != user.getId()) {
			throw new NoAuthorityException();
		}
		uiModel.addAttribute("instrument", instrument);
		uiModel.addAttribute("brands", brands);
		uiModel.addAttribute("categories", categories);
		uiModel.addAttribute("thirdCategories", thirdCategories);
		return EDIT;
	}

	@RequestMapping(value = "/provider/instruments/{id}", params = "edit", method = RequestMethod.POST)
	public String update(Model uiModel, @PathVariable("id") Long id,
			@Valid @ModelAttribute("instrument") Instrument instrument,
			BindingResult bindingResult) {
		instrument.setId(id);
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("instrument", instrument);
			return EDIT;
		}
		List<Brand> brands = brandService.findByAcitvate(ActivateEnum.ACTIVATE);
		List<ProductCategory> categories = categoryService
				.findAllSecondCategory(ActivateEnum.ACTIVATE);
		List<ProductCategory> thirdCategories = categoryService
				.findByParentAndActivate(instrument.getSecondCategory(),
						ActivateEnum.ACTIVATE);
		Instrument result = instrumentService.update(instrument);
		uiModel.addAttribute("instrument", result);
		uiModel.addAttribute("brands", brands);
		uiModel.addAttribute("categories", categories);
		uiModel.addAttribute("thirdCategories", thirdCategories);
		return "redirect:/provider/instruments/" + result.getId() + "?edit";
	}

	@RequestMapping(value = "/instruments/{id}", method = RequestMethod.GET)
	public String view(Model uiModel, @PathVariable(value = "id") Long id) {
		Instrument instrument = instrumentService.findOne(id);
		Page<Review> reviews = reviewService.findByProduct(instrument,
				new PageRequest(0, 15, Direction.DESC, "createdTime"));
		uiModel.addAttribute("instrument", instrument);
		uiModel.addAttribute("reviews", reviews);
		return VIEW;
	}
}
