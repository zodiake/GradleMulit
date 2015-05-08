package com.sj.admin.async;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;

@Service
public class AsyncWriteFileServiceImpl implements AsyncWriteFileService {
	@Override
	@Async
	public void writeToFile(MultipartFile file) {
		InputStream stream;
		try {
			stream = file.getInputStream();
			byte[] bytes = new byte[1024 * 100];
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
	}
}
