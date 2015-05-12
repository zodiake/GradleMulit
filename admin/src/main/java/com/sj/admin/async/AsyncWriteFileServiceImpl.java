package com.sj.admin.async;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sj.model.model.UploadResult;
import com.sj.model.model.UploadResultDetail;
import com.sj.repository.util.FileUtil;

@Service
public class AsyncWriteFileServiceImpl implements AsyncWriteFileService {
	private final String imgPath = "src/main/resources/static/upload/img/";
	private final String audioPath = "src/main/resources/static/upload/audio/";

	private final String imgUrl = "/upload/img/";
	private final String audioUrl = "/upload/audio/";

	@Override
	public UploadResult writeToFile(MultipartFile file) {
		InputStream stream;
		String fileName = FileUtil.getFileName(file.getContentType());
		try {
			stream = file.getInputStream();
			Path basePath = Paths.get("").resolve(imgPath + fileName);
			Files.copy(stream, basePath);
			stream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return getResult(file, fileName, true);
	}
	
	@Override
	public UploadResult writeBigToFile(MultipartFile file) {
		return null;
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
