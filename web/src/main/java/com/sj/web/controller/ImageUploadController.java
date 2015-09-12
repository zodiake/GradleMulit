package com.sj.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sj.web.security.UserContext;

@Controller
public class ImageUploadController extends UploadController{
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private UserContext userContext;

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
