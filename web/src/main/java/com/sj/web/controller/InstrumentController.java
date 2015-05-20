package com.sj.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.model.model.Instrument;
import com.sj.model.model.Provider;
import com.sj.model.model.Review;
import com.sj.repository.search.model.InstrumentSearchOption;
import com.sj.repository.search.service.InstrumentSearchService;
import com.sj.repository.service.InstrumentService;
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
	private InstrumentSearchService searchService;

	private final String CREATE = "instrument/create";
	private final String EDIT = "instrument/edit";
	private final String VIEW = "instrument/view";
	private final String LIST = "instrument/list";

	@RequestMapping(value = "/provider/instruments", params = "form", method = RequestMethod.GET)
	public String craete(Model uiModel) {
		Instrument instrument = new Instrument();
		uiModel.addAttribute("instrument", instrument);
		return CREATE;
	}

	@RequestMapping(value = "/provider/instruments", params = "form", method = RequestMethod.POST)
	public String createInstrument(Model uiModel,
			@Valid @ModelAttribute("instrument") Instrument instrument,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
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
	public String edit() {
		return EDIT;
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

	@RequestMapping(value = "/instruments", method = RequestMethod.GET)
	public String list(Model uiModel,
			@PageableDefault(size = 15) Pageable pageable,
			@ModelAttribute("option") InstrumentSearchOption option) {
		searchService.findByOption(option, pageable);
		return LIST;
	}
}
