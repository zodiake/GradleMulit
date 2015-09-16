package com.sj.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/admin")
public class IndexController extends UploadController {

	@RequestMapping(value = "/img/upload", method = RequestMethod.POST)
	@ResponseBody
	public List<String> uploadImage(MultipartFile file) {
		String result = super.upload(file);
		ArrayList<String> list = new ArrayList<>();
		list.add("http://127.0.0.1:8000/" + result);
		if (result != null)
			return list;
		return null;
	}

	@RequestMapping(value = "/editor/img/upload", method = RequestMethod.POST)
	@ResponseBody
	private void upload(
			@RequestParam(value = "upload", required = false) MultipartFile file,
			@RequestParam("CKEditorFuncNum") String num,
			HttpServletResponse response) {
		String fileName = super.upload(file);
		try {
			sendScript(response, num, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendScript(HttpServletResponse response, String num,
			String file) throws IOException {
		PrintWriter writer = response.getWriter();
		writer = response.getWriter();
		response.setContentType("text/html");
		writer.write("<script type=\"text/javascript\">");
		writer.write("window.parent.CKEDITOR.tools.callFunction(" + num
				+ ",'http://localhost:8000/" + file + "','')");
		writer.write("</script>");
		writer.close();
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

	@RequestMapping(value = "/info")
	public String info() {
		return "information/info";
	}

	@RequestMapping(value = "/templates/info/detail")
	public String infoDetail() {
		return "information/create";
	}

	@RequestMapping(value = "/info", params = "form")
	public String newInfo() {
		return "information/create";
	}

	@RequestMapping(value = "/subject")
	public String subject() {
		return "subject/subjects";
	}

	// subject detail
	@RequestMapping(value = "/templates/subject")
	public String subjectView() {
		return "subject/edit";
	}

	// create a subject
	@RequestMapping(value = "/subject/create")
	public String subjectCreate() {
		return "subject/create";
	}

	@RequestMapping(value = "/templates/solutions")
	public String solution() {
		return "subject/solution/list";
	}

	@RequestMapping(value = "/solutions", params = "form")
	public String newSolution() {
		return "subject/solution/create";
	}

	@RequestMapping(value = "/templates/products")
	public String products() {
		return "product/list";
	}

	@RequestMapping(value = "/templates/products/detail")
	public String productDetail(Model uiModel) {
		return "product/detail";
	}

	@RequestMapping(value = "/category")
	public String category(Model uiModel) {
		return "category/category";
	}

	@RequestMapping(value = "/categoryAdd")
	public String categoryAdd() {
		return "category/categoryAdd";
	}

	@RequestMapping(value = "/childCategoryAdd")
	public String childCategoryAdd() {
		return "category/childCategoryAdd";
	}

	@RequestMapping(value = "/childCategory")
	public String childCategory() {
		return "category/childCategory";
	}

	@RequestMapping(value = "/categoryEdit")
	public String categoryEdit() {
		return "category/categoryEdit";
	}

	@RequestMapping(value = "/templates/scrollImg")
	public String scrollImg() {
		return "scroll/list";
	}

	@RequestMapping(value = "/templates/scrollImg/detail")
	public String scrollImgDetail() {
		return "scroll/detail";
	}
}
