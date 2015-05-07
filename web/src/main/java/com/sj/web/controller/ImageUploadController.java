package com.sj.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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

		String userFold = userContext.getCurrentUser().getId().toString();
		Path userDir = Paths.get(userFold);
		Path basePath = Paths.get("").resolve(
				"src/main/resources/static/upload");
		Calendar c = Calendar.getInstance();
		String fileName = String.valueOf(c.hashCode())
				+ StringUtils.trimAllWhitespace(file.getOriginalFilename());

		try {
			Path uploadFilePath = getUploadDir(basePath, userDir, fileName);
			byte[] bytes = file.getBytes();
			Files.write(uploadFilePath, bytes);
			sendScript(response, num, userFold, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Path getUploadDir(Path baseDir, Path userDir, String fileName)
			throws IOException {
		Path temp = baseDir.resolve(userDir);
		if (!Files.exists(temp))
			Files.createDirectories(temp);
		return Paths.get(temp.toString() + "/" + fileName);
	}

	private void sendScript(HttpServletResponse response, String num,
			String foldName, String imageFile) throws IOException {
		PrintWriter writer = response.getWriter();
		writer = response.getWriter();
		response.setContentType("text/html");
		writer.write("<script type=\"text/javascript\">");
		writer.write("window.parent.CKEDITOR.tools.callFunction(" + num + ",'"
				+ "/upload/" + foldName + "/" + imageFile + "','')");
		writer.write("</script>");
		writer.close();
	}
}
