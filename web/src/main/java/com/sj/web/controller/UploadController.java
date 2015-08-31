package com.sj.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.sj.model.model.Provider;
import com.sj.model.model.UploadResult;
import com.sj.web.async.AsyncWriteFileService;

public class UploadController {
	@Autowired
	protected AsyncWriteFileService writeFileService;

	public UploadResult upload(MultipartFile file) {
		return writeFileService.writeToFile(file);
	}

	public UploadResult uploadAutio(MultipartFile file) {
		return writeFileService.writeBigToFile(file);
	}

	public UploadResult uploadProduct(MultipartFile file, Provider provider) {
		return writeFileService.writeProductToProviderFile(file, provider);
	}
}
