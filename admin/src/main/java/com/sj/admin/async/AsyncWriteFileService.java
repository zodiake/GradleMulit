package com.sj.admin.async;

import org.springframework.web.multipart.MultipartFile;

public interface AsyncWriteFileService {
	public void writeToFile(MultipartFile file);
}
