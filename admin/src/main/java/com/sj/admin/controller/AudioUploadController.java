package com.sj.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sj.model.model.UploadResult;

@Controller
public class AudioUploadController extends UploadController {

	@RequestMapping(value = "/admin/audio", method = RequestMethod.POST)
	@ResponseBody
	public UploadResult uploadAudio(MultipartFile file) {
		return writeFileService.writeBigToFile(file);
	}
}
