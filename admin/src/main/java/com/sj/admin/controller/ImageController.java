package com.sj.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.sj.admin.async.AsyncWriteFileService;
import com.sj.admin.util.UploadFileEnum;
import com.sj.model.model.UploadResult;
import com.sj.repository.util.FileUtil;

public class ImageController {
	@Autowired
	protected AsyncWriteFileService writeFileService;

	public UploadResult upload(MultipartFile file, HttpServletRequest request) {
		String contentType = file.getContentType();
		String fileName = FileUtil.getFileName(contentType);
		writeFileService.writeToFile(file, UploadFileEnum.IMAGE, fileName);
		return writeFileService.getResult(file, UploadFileEnum.IMAGE, fileName);
	}
}
