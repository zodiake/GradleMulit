package com.sj.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sj.model.model.Provider;
import com.sj.model.model.SiteUser;
import com.sj.model.model.UploadResult;
import com.sj.web.annotation.SecurityUser;

@Controller
public class ImageController extends UploadController {
	@RequestMapping(value = "/businessLicenseUrl", method = RequestMethod.POST)
	@ResponseBody
	public UploadResult businessLicenseUpload(MultipartFile file) {
		UploadResult result = super.upload(file);
		return result;
	}

	@RequestMapping(value = "/taxRegistrationUrl", method = RequestMethod.POST)
	@ResponseBody
	public UploadResult taxRegistrationUpload(MultipartFile file) {
		UploadResult result = super.upload(file);
		return result;
	}

	@RequestMapping(value = "/structureCodeUrl", method = RequestMethod.POST)
	@ResponseBody
	public UploadResult structureCodeUpload(MultipartFile file) {
		UploadResult result = super.upload(file);
		return result;
	}

	@RequestMapping(value = "/provider/productImage", method = RequestMethod.POST)
	@ResponseBody
	public UploadResult productUpload(MultipartFile file,
			@SecurityUser SiteUser user) {
		UploadResult result = super.uploadProduct(file,
				new Provider(user.getId()));
		return result;
	}

}
