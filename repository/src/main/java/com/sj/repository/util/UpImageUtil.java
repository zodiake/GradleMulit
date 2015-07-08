package com.sj.repository.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class UpImageUtil {
	public static String saveImage(MultipartFile mf, String name, String type) {
		Path userDir = Paths.get(name);
		Path basePath = Paths.get("").resolve(
				"src/main/resources/static/upload");
		String fileName = type
				+ StringUtils.trimAllWhitespace(mf.getOriginalFilename()
						.substring(mf.getOriginalFilename().lastIndexOf(".")));
		try {
			Path uploadFilePath = getUploadDir(basePath, userDir, fileName);
			if(Files.exists(uploadFilePath)){
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
		Path temp = baseDir.resolve(userDir);
		if (!Files.exists(temp))
			Files.createDirectories(temp);
		return Paths.get(temp.toString() + "/" + fileName);
	}
}
