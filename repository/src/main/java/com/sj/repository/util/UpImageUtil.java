package com.sj.repository.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class UpImageUtil {
	public static final String PROVIDERBASE = "src/main/resources/static/upload";
	public static final String PRODUCTBASE = "src/main/resources/static/product";
	public static final String BRANDBASE = "src/main/resources/static/upload/brand";
	public static final String CONTENTBASE = "src/main/resources/static/upload/content";

	public static String saveImage(MultipartFile mf, String name,
			String fileName, String base) {
		Path userDir = Paths.get(name);
		Path basePath = Paths.get("").resolve(base);
		try {
			Path uploadFilePath = getUploadDir(basePath, userDir, fileName);
			if (Files.exists(uploadFilePath)) {
				Files.delete(uploadFilePath);
			}
			byte[] bytes = mf.getBytes();
			Files.write(uploadFilePath, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public static String saveImage(MultipartFile mf, String fileName,
			String base) {
		Path basePath = Paths.get("").resolve(base);
		try {
			Path uploadFilePath = getUploadDir(basePath, null, fileName);
			if (Files.exists(uploadFilePath)) {
				Files.delete(uploadFilePath);
			}
			byte[] bytes = mf.getBytes();
			Files.write(uploadFilePath, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public static Path getUploadDir(Path baseDir, Path userDir, String fileName)
			throws IOException {
		if (userDir == null) {
			return Paths.get(baseDir + "/" + fileName);
		}
		Path temp = baseDir.resolve(userDir);
		if (!Files.exists(temp))
			Files.createDirectories(temp);
		return Paths.get(temp.toString() + "/" + fileName);
	}
}
