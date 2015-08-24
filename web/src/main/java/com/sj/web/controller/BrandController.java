package com.sj.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sj.model.model.Brand;
import com.sj.model.model.Product;
import com.sj.model.model.Subject;
import com.sj.repository.service.ProductService;
import com.sj.repository.util.UpImageUtil;
import com.sj.web.util.ShowPage;

@Controller
public class BrandController {
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/brand/{id}", method = RequestMethod.GET)
	public String findByBrand(@PathVariable("id") Long id, Model uiModel,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size) {
		Page<Product> products = productService.findByBrand(new PageRequest(
				page - 1, size), new Brand(id));
		ShowPage.showProduct(products);
		uiModel.addAttribute("products", products);
		return null;
	}

	@RequestMapping(value = "/cheditor", method = RequestMethod.GET)
	public String test(Model uiModel) {
		uiModel.addAttribute("product", new Product());
		return "test";
	}

	@RequestMapping(value = "/cheditor", method = RequestMethod.POST)
	public String testProcess(@RequestParam("product") Product product) {
		System.out.println(product.getContent().getContent());
		return "index";
	}

	@RequestMapping(value = "/mtest")
	public String testMp4(Model uiModel) {
		Subject s = new Subject();
		uiModel.addAttribute("subject", s);
		return "mtest";	
	}

	@RequestMapping(value = "/aaa/upload", method = RequestMethod.POST)
	@ResponseBody
	public String t(@RequestParam("upload") MultipartFile mf,
			HttpServletResponse response, HttpServletRequest request,
			@RequestParam("CKEditorFuncNum") String callback)
			throws IOException {
		String name = String.valueOf(new Date().getTime());
		String fileName = name
				+ mf.getOriginalFilename().substring(
						mf.getOriginalFilename().indexOf("."));
		UpImageUtil.saveImage(mf, fileName, UpImageUtil.CONTENTBASE);

		String str = "<script type=\"text/javascript\">";
		str = str + "window.parent.CKEDITOR.tools.callFunction(" + callback
				+ ",'" +"/upload/content/" + fileName + "','')";
		str = str + "</script>";
		return str;
	}
}
