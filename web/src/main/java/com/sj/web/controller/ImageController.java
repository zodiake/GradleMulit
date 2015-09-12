package com.sj.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

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
	public ArrayList<String> businessLicenseUpload(MultipartFile file) {
		String result = super.upload(file);
		ArrayList<String> list = new ArrayList<>();
		list.add("http://localhost:8000/" + result);
		if (result != null)
			return list;
		return null;
	}

	@RequestMapping(value = "/taxRegistrationUrl", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<String> taxRegistrationUpload(MultipartFile file) {
		String result = super.upload(file);
		ArrayList<String> list = new ArrayList<>();
		list.add("http://localhost:8000/" + result);
		if (result != null)
			return list;
		return null;
	}

	@RequestMapping(value = "/structureCodeUrl", method = RequestMethod.POST)
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
