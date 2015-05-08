package com.sj.admin.async;

import java.nio.file.Path;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

public interface AsyncWriteFileService {
	public void writeToFile(MultipartFile file);
}
