package com.sj.admin.async;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sj.model.model.UploadResult;
import com.sj.model.model.UploadResultDetail;
import com.sj.repository.util.FileUtil;

@Service
public class AsyncWriteFileServiceImpl implements AsyncWriteFileService {
	private final String imgPath = "﻿D:/web/imgServer/pic/public";
	private final String audioPath = "src/main/resources/static/upload/audio/";

	private final String imgUrl = "/upload/img/";
	private final String audioUrl = "/upload/audio/";

	@Override
	public String writeToFile(MultipartFile file) {
		InputStream stream;
		String fileName = FileUtil.getFileName(file.getContentType());
		try {
			stream = file.getInputStream();
			String year = Calendar.getInstance().get(1) + "";
			String month = Calendar.getInstance().get(2) + "";
			Path basePath = Paths.get("D:", "web", "imgServer", "pic",
					"public", year, month);
			if (Files.notExists(basePath)) {
				Files.createDirectories(basePath);
			}
			
			Path filePath=Paths.get(basePath.toString(), fileName);
			Files.copy(stream, filePath);
			stream.close();
			return year + "/" + month + "/" + fileName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Async
	public UploadResult writeBigToFile(MultipartFile file) {
		InputStream stream;
		String fileName = FileUtil.getFileName(file.getContentType());
		try {
			stream = file.getInputStream();
			Path basePath = Paths.get("").resolve(audioPath + fileName);
			IOUtils.copyLarge(stream, Files.newOutputStream(basePath));
			stream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return getResult(file, fileName, true);
	}

	private UploadResult getResult(MultipartFile file, String fileName,
			boolean isImage) {
		UploadResult result = new UploadResult();
		List<UploadResultDetail> files = new ArrayList<>();
		if (isImage)
			files.add(new UploadResultDetail(file.getOriginalFilename(), file
					.getSize(), imgUrl + fileName, "asd", "delete"));
		else
			files.add(new UploadResultDetail(file.getOriginalFilename(), file
					.getSize(), audioUrl + fileName, "asd", "delete"));

		result.setFiles(files);
		return result;
	}
}
