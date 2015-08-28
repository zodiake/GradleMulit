package com.sj.admin.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sj.model.model.UploadResult;

@Controller
@RequestMapping(value = "/admin")
public class IndexController extends UploadController {

	@RequestMapping(value = "/img/upload", method = RequestMethod.POST)
	@ResponseBody
	public List<String> uploadImage(MultipartFile file) {
		UploadResult result = super.upload(file);
		List<String> url = result.getFiles().stream().map(f -> f.getUrl())
				.collect(Collectors.toList());
		return url;
	}

	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/commonUser")
	public String commonUser() {
		return "user/user";
	}

	@RequestMapping(value = "/commonUserDetail")
	public String commonUserDetail() {
		return "user/userDetail";
	}

	@RequestMapping(value = "/provider")
	public String provider() {
		return "user/provider";
	}

	@RequestMapping(value = "/providerDetail")
	public String providerDetail() {
		return "user/providerDetail";
	}

	@RequestMapping(value = "/brand")
	public String brand() {
		return "brand/brand";
	}

	@RequestMapping(value = "/brandDetail")
	public String brandDetail() {
		return "brand/brandDetail";
	}

	@RequestMapping(value = "/advertisement")
	public String advertise() {
		return "advertisement/advertisement";
	}

	@RequestMapping(value = "/advertise", params = "create")
	public String createAdvertise() {
		return "advertisement/createAdvertise";
	}

	@RequestMapping(value = "/products")
	public String productList() {
		return "product/product";
	}

	@RequestMapping(value = "/info")
	public String info() {
		return "information/info";
	}

	@RequestMapping(value = "/info", params = "form")
	public String newInfo() {
		return "information/create";
	}
}
