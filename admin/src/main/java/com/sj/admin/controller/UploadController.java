package com.sj.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.sj.admin.async.AsyncWriteFileService;
import com.sj.model.model.UploadResult;

public class UploadController {
	@Autowired
	protected AsyncWriteFileService writeFileService;

	public UploadResult upload(MultipartFile file, HttpServletRequest request) {
		return writeFileService.writeToFile(file);
	}

	public String uploadAutio(MultipartFile file, HttpServletRequest request) {
		return "";
	}
}
