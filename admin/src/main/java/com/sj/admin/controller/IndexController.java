package com.sj.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class IndexController {
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

	@RequestMapping(value = "/createAdvertise")
	public String createAdvertise() {
		return "advertisement/createAdvertise";
	}

	@RequestMapping(value = "/products")
	public String productList() {
		return "product/product";
	}
}
