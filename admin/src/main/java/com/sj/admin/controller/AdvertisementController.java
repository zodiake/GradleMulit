package com.sj.admin.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sj.admin.async.AsyncWriteFileService;
import com.sj.admin.util.UploadFileEnum;
import com.sj.model.model.Advertisement;
import com.sj.model.model.UploadResult;
import com.sj.model.model.UploadResultDetail;
import com.sj.model.type.AdvertiseCategoryEnum;
import com.sj.repository.service.AdvertisementService;

@Controller
public class AdvertisementController {
	@Autowired
	private AdvertisementService service;
	@Autowired
	private AsyncWriteFileService writeFileService;

	private final String LIST = "advertisement/list";
	private final String EDIT = "advertisement/edit";

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

	@RequestMapping(value = "/admin/{category}/advertisements/{id}", params = "edit", method = RequestMethod.GET)
	public String edit(Model uiModel,
			@PathVariable(value = "category") String category,
			@PathVariable(value = "id") Long id) {
		AdvertiseCategoryEnum categoryEnum = AdvertiseCategoryEnum
				.fromString(category);
		Advertisement adv = service.findByIdAndCategory(id, categoryEnum);
		uiModel.addAttribute("adv", adv);
		return EDIT;
	}

	@RequestMapping(value = "/admin/{category}/advertisements/{id}", method = RequestMethod.POST)
	@ResponseBody
	public UploadResult upload(@PathVariable("id") Long id, MultipartFile file,
			HttpServletRequest request) {

		Calendar c = Calendar.getInstance();
		String fileName = String.valueOf(c.hashCode())
				+ StringUtils.trimAllWhitespace(file.getOriginalFilename());

		writeFileService.writeToFile(file, UploadFileEnum.IMAGE, fileName);
		UploadResult result = new UploadResult();
		List<UploadResultDetail> files = new ArrayList<>();
		files.add(new UploadResultDetail(file.getOriginalFilename(), file
				.getSize(), "/upload/img/" + fileName, "asd", "delete"));
		result.setFiles(files);
		return result;
	}
}
