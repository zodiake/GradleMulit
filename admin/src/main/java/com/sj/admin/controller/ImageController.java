package com.sj.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sj.admin.async.AsyncWriteFileService;
import com.sj.admin.util.UploadFileEnum;
import com.sj.model.model.UploadResult;
import com.sj.repository.util.FileUtil;

@Controller
public class ImageController {
	@Autowired
	private AsyncWriteFileService writeFileService;

	@RequestMapping(value = "/admin/images", method = RequestMethod.POST)
	@ResponseBody
	public UploadResult upload(MultipartFile file, HttpServletRequest request) {
		String contentType = file.getContentType();
		String fileName = FileUtil.getFileName(contentType);
		writeFileService.writeToFile(file, UploadFileEnum.IMAGE, fileName);
		return writeFileService.getResult(file, UploadFileEnum.IMAGE, fileName);
	}
}
