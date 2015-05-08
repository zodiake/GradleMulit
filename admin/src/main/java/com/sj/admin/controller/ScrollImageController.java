package com.sj.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sj.admin.async.AsyncWriteFileService;
import com.sj.model.model.ScrollImage;
import com.sj.model.model.UploadResult;
import com.sj.model.model.UploadResultDetail;
import com.sj.repository.service.ScrollImageService;

@Controller
public class ScrollImageController {
	@Autowired
	private ScrollImageService service;
	@Autowired
	private AsyncWriteFileService writeFileService;

	private final String LISTS = "scroll/list";

	@RequestMapping(value = "/admin/scrollImages", method = RequestMethod.GET)
	public String lists(Model uiModel) {
		List<ScrollImage> result = service.findAll();
		uiModel.addAttribute("lists", result);
		return LISTS;
	}

	@RequestMapping(value = "/admin/scrollImages/{id}", method = RequestMethod.POST)
	@ResponseBody
	public UploadResult upload(@PathVariable("id") Long id, MultipartFile file,
			HttpServletRequest request) {
		writeFileService.writeToFile(file);
		UploadResult result = new UploadResult();
		List<UploadResultDetail> files = new ArrayList<>();
		files.add(new UploadResultDetail(file.getOriginalFilename(), file
				.getSize(), "", "asd", "delete"));
		result.setFiles(files);
		return result;
	}
}
