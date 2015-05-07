package com.sj.admin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;

@Controller
public class AudioUploadController {
	private final String UPLOAD = "audio/upload";

	@RequestMapping(value = "/audioUpload", method = RequestMethod.GET)
	public String uploadAudio() {
		return UPLOAD;
	}

	@RequestMapping(value = "/audioUpload", method = RequestMethod.POST)
	public String upload(MultipartFile file, HttpServletRequest request) {
		try {
			InputStream stream = file.getInputStream();
			byte[] bytes = new byte[10240];
			Path basePath = Paths.get("").resolve(
					"src/main/resources/static/upload/audio/"
							+ file.getOriginalFilename());
			while (stream.read(bytes) != -1) {
				Files.write(bytes, basePath.toFile());
			}
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return UPLOAD;
	}
}
