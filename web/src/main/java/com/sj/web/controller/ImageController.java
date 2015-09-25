package com.sj.web.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController extends UploadController {
	@RequestMapping(value = {"/businessLicenseUrl","/supplier/businessLicenseUrl"}, method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<String> businessLicenseUpload(MultipartFile file) {
		String result = super.upload(file);
		ArrayList<String> list = new ArrayList<>();
		list.add("http://localhost:8000/" + result);
		if (result != null)
			return list;
		return null;
	}

	@RequestMapping(value = {"/taxRegistrationUrl","/supplier/taxRegistrationUrl"}, method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<String> taxRegistrationUpload(MultipartFile file) {
		String result = super.upload(file);
		ArrayList<String> list = new ArrayList<>();
		list.add("http://localhost:8000/" + result);
		if (result != null)
			return list;
		return null;
	}

	@RequestMapping(value = {"/structureCodeUrl","/supplier/structureCodeUrl"}, method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<String> structureCodeUpload(MultipartFile file) {
		String result = super.upload(file);
		ArrayList<String> list = new ArrayList<>();
		list.add("http://localhost:8000/" + result);
		if (result != null)
			return list;
		return null;
	}

	@RequestMapping(value = "/provider/productImage", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<String> productUpload(MultipartFile file) {
		String result = super.upload(file);
		ArrayList<String> list = new ArrayList<>();
		list.add("http://localhost:8000/" + result);
		if (result != null)
			return list;
		return null;
	}
	@RequestMapping(value = "/provider/img/upload", method = RequestMethod.POST)
	@ResponseBody
	public void upload(
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
}