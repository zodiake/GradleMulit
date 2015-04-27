package com.sj.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.model.model.Instrument;
import com.sj.model.model.Provider;
import com.sj.repository.service.InstrumentService;
import com.sj.web.security.UserContext;

@Controller
public class InstrumentController {
	@Autowired
	private InstrumentService instrumentService;
	@Autowired
	private UserContext userContext;

	private final String CREATE = "instrument/create";
	private final String EDIT = "instrument/edit";

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
		instrumentService.save(instrument);
		return "redirect:/instruments/";
	}

	@RequestMapping(value = "/provider/instruments/{id}", params = "edit", method = RequestMethod.GET)
	public String edit() {
		return EDIT;
	}

}
