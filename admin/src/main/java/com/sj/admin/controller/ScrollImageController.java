package com.sj.admin.controller;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sj.model.model.ScrollImage;
import com.sj.model.model.UploadResult;
import com.sj.model.type.ScrollImageType;
import com.sj.repository.service.ScrollImageService;

@Controller
public class ScrollImageController extends UploadController {
	@Autowired
	private ScrollImageService service;

	private final String LISTS = "scroll/list";

	@RequestMapping(value = "/admin/{type}/scrollImages", method = RequestMethod.GET)
	public String lists(Model uiModel,
			@PathVariable(value = "type") ScrollImageType type) {
		List<ScrollImage> result = service.findAll(type, new PageRequest(0, 6,
				Direction.DESC, "sortNumber"));
		uiModel.addAttribute("lists", result);
		uiModel.addAttribute("type", type.toString());
		return LISTS;
	}

	@RequestMapping(value = "/admin/scrollImages/{type}/{id}", method = RequestMethod.POST)
	@ResponseBody
	public UploadResult upload(MultipartFile file, HttpServletRequest request,
			@PathVariable(value = "id") Long id) {
		UploadResult result = super.upload(file);
		List<String> url = result.getFiles().stream().map(f -> f.getUrl())
				.collect(toList());
		ScrollImage scroll = new ScrollImage(id);
		scroll.setImageUrl(url.get(0));
		service.update(scroll);
		return result;
	}

	@RequestMapping(value = "/admin/{type}/scrollImages", method = RequestMethod.PUT)
	@ResponseBody
	public void freshCache(@PathVariable(value = "type") ScrollImageType type) {
		service.freshCache(type);
	}

}