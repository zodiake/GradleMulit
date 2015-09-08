package com.sj.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.model.model.Instrument;
import com.sj.model.model.Review;
import com.sj.repository.service.BrandService;
import com.sj.repository.service.InstrumentService;
import com.sj.repository.service.ProductCategoryService;
import com.sj.repository.service.ReviewService;
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

	@RequestMapping(value = "/instruments/{id}", method = RequestMethod.GET)
	public String view(Model uiModel, @PathVariable(value = "id") Long id) {
		Instrument instrument = instrumentService.findOne(id);
		Page<Review> reviews = reviewService.findByProduct(instrument,
				new PageRequest(0, 15, Direction.DESC, "createdTime"));
		uiModel.addAttribute("instrument", instrument);
		uiModel.addAttribute("reviews", reviews);
		return "product/test";
	}
	@RequestMapping(value = "/instruments",method = RequestMethod.GET,params="form")
	public String create(Model uiModel){
		uiModel.addAttribute("instrument", new Instrument());
		return "instrument/create";
	}
}
