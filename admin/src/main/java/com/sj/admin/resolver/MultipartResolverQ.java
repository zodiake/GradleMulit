package com.sj.admin.resolver;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class MultipartResolverQ extends CommonsMultipartResolver {
	private DeferredResult<String> deferred;

	public MultipartResolverQ(DeferredResult<String> result) {
		super();
		this.deferred = result;
	}

	@Override
	protected MultipartParsingResult parseRequest(HttpServletRequest request)
			throws MultipartException {
		String encoding = determineEncoding(request);
		FileUpload fileUpload = prepareFileUpload(encoding);
		fileUpload.setProgressListener(new ProgressListener() {
			@Override
			public void update(long pBytesRead, long pContentLength, int pItems) {
				
			}
		});
		try {
			List<FileItem> fileItems = ((ServletFileUpload) fileUpload)
					.parseRequest(request);
			return parseFileItems(fileItems, encoding);
		} catch (FileUploadBase.SizeLimitExceededException ex) {
			throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(),
					ex);
		} catch (FileUploadException ex) {
			throw new MultipartException(
					"Could not parse multipart servlet request", ex);
		}
	}
}
