package com.sj.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sj.admin.async.AsyncWriteFileService;
import com.sj.model.model.UploadResult;
import com.sj.model.model.UploadResultDetail;

@Controller
public class AudioUploadController {
	@Autowired
	private AsyncWriteFileService writeFileService;

	private final String UPLOAD = "audio/upload";

	@RequestMapping(value = "/audioUpload", method = RequestMethod.GET)
	public String uploadAudio() {
		return UPLOAD;
	}

	@RequestMapping(value = "/audioUpload", method = RequestMethod.POST)
	@ResponseBody
	public UploadResult upload(MultipartFile file, HttpServletRequest request) {
		writeFileService.writeToFile(file);
		UploadResult result = new UploadResult();
		List<UploadResultDetail> files = new ArrayList<>();
		files.add(new UploadResultDetail(file.getOriginalFilename(), file
				.getSize(), "asd", "asd", "delete"));
		result.setFiles(files);
		return result;
	}
}