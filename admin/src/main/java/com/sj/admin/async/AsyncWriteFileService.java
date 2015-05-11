package com.sj.admin.async;

import org.springframework.web.multipart.MultipartFile;

import com.sj.admin.util.UploadFileEnum;
import com.sj.model.model.UploadResult;

public interface AsyncWriteFileService {
	public void writeToFile(MultipartFile file, UploadFileEnum fileType,
			String fileName);

	public UploadResult getResult(MultipartFile file,UploadFileEnum type,String fileName);
}
