package com.sj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.sj.admin.async.AsyncWriteFileService;
import com.sj.model.model.UploadResult;

public class UploadController {
	@Autowired
	protected AsyncWriteFileService writeFileService;

	public String upload(MultipartFile file) {
		return writeFileService.writeToFile(file);
	}

	public UploadResult uploadAutio(MultipartFile file) {
		return writeFileService.writeBigToFile(file);
	}
}
