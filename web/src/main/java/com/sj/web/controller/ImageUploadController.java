package com.sj.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sj.web.security.UserContext;

@Controller
public class ImageUploadController {
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private UserContext userContext;

	@RequestMapping(value = "/img/upload", method = RequestMethod.POST)
	@ResponseBody
	private void upload(
			@RequestParam(value = "upload", required = false) MultipartFile file,
			@RequestParam("CKEditorFuncNum") String num,
			HttpServletResponse response) {
		Path imgPath = Paths.get("")
				.resolve("src/main/resources/static/upload")
				.resolve(userContext.getCurrnetUser().getId().toString());

		String imageFile = file.getOriginalFilename();

		try {
			if (!Files.exists(imgPath)) {
				Files.createDirectory(imgPath);
			}
			byte[] bytes = file.getBytes();
			imageFile = incrementFileName(imageFile);
			Path result = Paths.get(imgPath.toString() + "/" + imageFile);
			System.out.println(result.toString() + "--------");

			Files.write(result, bytes);
			sendScript(response, num, imageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String incrementFileName(String name) {
		String fileName = name.substring(0, name.lastIndexOf("."));
		String suffix = name.substring(name.lastIndexOf("."));
		int begin = fileName.lastIndexOf("_");
		System.out.println(begin + "]]]]");
		if (begin != -1) {
			String inc = name.substring(begin + 1);
			int i = Integer.valueOf(inc) + 1;
			String source = name.substring(0, begin);
			return source + "_" + i;
		} else {
			return name + "_1" + suffix;
		}
	}

	private void sendScript(HttpServletResponse response, String num,
			String imageFile) throws IOException {
		PrintWriter writer = response.getWriter();
		writer = response.getWriter();
		response.setContentType("text/html");
		writer.write("<script type=\"text/javascript\">");
		writer.write("window.parent.CKEDITOR.tools.callFunction(" + num + ",'"
				+ "/upload/" + imageFile + "','')");
		writer.write("</script>");
		writer.close();
	}
}
