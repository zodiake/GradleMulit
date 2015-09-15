package com.sj.web.async;

import org.springframework.web.multipart.MultipartFile;

import com.sj.model.model.UploadResult;

public interface AsyncWriteFileService {
	public UploadResult writeBigToFile(MultipartFile file);

	public String writeToFile(MultipartFile file);
	
//	public UploadResult writeProductToProviderFile(MultipartFile file,Provider provider);
	
}
