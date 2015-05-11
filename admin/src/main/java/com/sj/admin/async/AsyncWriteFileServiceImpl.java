package com.sj.admin.async;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sj.admin.util.UploadFileEnum;
import com.sj.model.model.UploadResult;
import com.sj.model.model.UploadResultDetail;

@Service
public class AsyncWriteFileServiceImpl implements AsyncWriteFileService {
	@Override
	public void writeToFile(MultipartFile file, UploadFileEnum fileType,
			String fileName) {
		InputStream stream;
		String dir = fileType.toString().toLowerCase(Locale.US);
		try {
			stream = file.getInputStream();
			Path basePath = Paths.get("").resolve(
					"src/main/resources/static/upload/" + dir + "/" + fileName);
			Files.copy(stream, basePath);
			stream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public UploadResult getResult(MultipartFile file, UploadFileEnum type,
			String fileName) {
		UploadResult result = new UploadResult();
		String dir = type.toString().toLowerCase(Locale.US);
		List<UploadResultDetail> files = new ArrayList<>();
		files.add(new UploadResultDetail(file.getOriginalFilename(), file
				.getSize(), "/upload/" + dir + "/" + fileName, "asd", "delete"));
		result.setFiles(files);
		return result;
	}
}
