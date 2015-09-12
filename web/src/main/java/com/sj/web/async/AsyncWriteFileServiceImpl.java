package com.sj.web.async;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sj.model.model.Provider;
import com.sj.model.model.UploadResult;
import com.sj.model.model.UploadResultDetail;
import com.sj.repository.util.FileUtil;

@Service
public class AsyncWriteFileServiceImpl implements AsyncWriteFileService {
	//private final String imgPath = "﻿D:/web/imgServer/pic/public";
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
			basePath.resolve(fileName);
			Files.copy(stream, basePath.toAbsolutePath());
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
//	private final String imgPath = "src/main/resources/static/upload/img/";
//	private final String audioPath = "src/main/resources/static/upload/audio/";
//
//	private final String imgUrl = "/upload/img/";
	private final String productUrl = "/upload/";
//	private final String audioUrl = "/upload/audio/";
//
//	@Override
//	public UploadResult writeToFile(MultipartFile file) {
//		InputStream stream;
//		String fileName = FileUtil.getFileName(file.getContentType());
//		try {
//			stream = file.getInputStream();
//			Path basePath = Paths.get("").resolve(imgPath + fileName);
//			Files.copy(stream, basePath);
//			stream.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return getResult(file, fileName, true);
//	}
//
//	@Override
//	@Async
//	public UploadResult writeBigToFile(MultipartFile file) {
//		InputStream stream;
//		String fileName = FileUtil.getFileName(file.getContentType());
//		try {
//			stream = file.getInputStream();
//			Path basePath = Paths.get("").resolve(audioPath + fileName);
//			IOUtils.copyLarge(stream, Files.newOutputStream(basePath));
//			stream.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return getResult(file, fileName, true);
//	}
//
//	private UploadResult getResult(MultipartFile file, String fileName,
//			boolean isImage) {
//		UploadResult result = new UploadResult();
//		List<UploadResultDetail> files = new ArrayList<>();
//		if (isImage)
//			files.add(new UploadResultDetail(file.getOriginalFilename(), file
//					.getSize(), imgUrl + fileName, "asd", "delete"));
//		else
//			files.add(new UploadResultDetail(file.getOriginalFilename(), file
//					.getSize(), audioUrl + fileName, "asd", "delete"));
//
//		result.setFiles(files);
//		return result;
//	}

	@Override
	public UploadResult writeProductToProviderFile(MultipartFile file,Provider provider) {
		String userFold = provider.getId().toString();
		Path userDir = Paths.get(userFold+"/");
		Path basePath = Paths.get("").resolve("src/main/resources/static/upload/");
		Calendar c = Calendar.getInstance();
		String fileName = String.valueOf(c.hashCode())
				+ StringUtils.trimAllWhitespace(file.getOriginalFilename());
		try {
			Path uploadFilePath = getUploadDir(basePath, userDir, fileName);
			byte[] bytes = file.getBytes();
			Files.write(uploadFilePath, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		UploadResult result = new UploadResult();
		List<UploadResultDetail> files = new ArrayList<>();
		files.add(new UploadResultDetail(file.getOriginalFilename(), file.getSize(), productUrl+userDir+"/" + fileName, "asd", "delete"));
		result.setFiles(files);
		return result;
	}
	private Path getUploadDir(Path baseDir, Path userDir, String fileName)
			throws IOException {
		Path temp = baseDir.resolve(userDir);
		if (!Files.exists(temp))
			Files.createDirectories(temp);
		return Paths.get(temp.toString() + "/" + fileName);
	}
}
