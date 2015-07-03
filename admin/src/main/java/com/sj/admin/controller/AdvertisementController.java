package com.sj.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sj.admin.async.AsyncWriteFileService;
import com.sj.model.model.Advertisement;
import com.sj.model.model.AdvertisementCategory;
import com.sj.model.model.UploadResult;
import com.sj.model.type.AdvertiseCategoryEnum;
import com.sj.repository.service.AdvertisementService;

@Controller
public class AdvertisementController extends UploadController {
	@Autowired
	private AdvertisementService service;
	@Autowired
	private AsyncWriteFileService writeFileService;

	private final String LIST = "advertisement/list";
	private final String EDIT = "advertisement/edit";
	private final String CREATE = "advertisement/create";
	private final String CTEATEOK = "";

	@RequestMapping(value = "/admin/{category}/advertisements", method = RequestMethod.GET)
	public String list(Model uiModel,
			@PathVariable(value = "category") String category) {
		AdvertiseCategoryEnum categoryEnum = AdvertiseCategoryEnum
				.fromString(category);
		List<Advertisement> lists = service.findByCategory(categoryEnum,
				new PageRequest(0, 5, Direction.DESC, "createdTime"));
		uiModel.addAttribute("list", lists);
		return LIST;
	}

	@RequestMapping(value = "/admin/{category}/advertisements", method = RequestMethod.GET, params = "form")
	public String createAdvertisement(Model uiModel) {
		uiModel.addAttribute("advertisement", new Advertisement());
		return CREATE;
	}

	@RequestMapping(value = "/admin/{category}/advertisements", method = RequestMethod.POST, params = "form")
	public String createAdvertisementProcess(
			Model uiModel,
			@PathVariable("category") AdvertisementCategory category,
			@Valid @ModelAttribute("advertisement") Advertisement advertisement,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("advertisement", advertisement);
			return CREATE;
		}
		advertisement.setCategory(category);
		service.save(advertisement);
		return CTEATEOK;
	}

	@RequestMapping(value = "/admin/{category}/advertisements/{id}", params = "edit", method = RequestMethod.GET)
	public String edit(Model uiModel,
			@PathVariable(value = "category") AdvertiseCategoryEnum category,
			@PathVariable(value = "id") Long id) {
		Advertisement adv = service.findByIdAndCategory(id, category);
		uiModel.addAttribute("adv", adv);
		return EDIT;
	}

	@RequestMapping(value = "/admin/{category}/advertisements/{id}", method = RequestMethod.PUT)
	public String upload(@ModelAttribute("adv") Advertisement adv,
			@PathVariable("id") Long id,
			@PathVariable("category") AdvertisementCategory category) {
		adv.setId(id);
		adv.setCategory(category);
		service.update(adv);
		return "redirect:/admin/" + category.toString().toLowerCase()
				+ "/advertisements/" + id + "?edit";
	}

	@RequestMapping(value = "/admin/{catgegory}/advertisements/{id}/coverImg", method = RequestMethod.POST)
	@ResponseBody
	public UploadResult uploadImage(MultipartFile file,
			HttpServletRequest request) {
		return super.upload(file);
	}
}
