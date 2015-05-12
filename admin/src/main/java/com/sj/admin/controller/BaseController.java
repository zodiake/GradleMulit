package com.sj.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.sj.admin.async.AsyncWriteFileService;
import com.sj.admin.util.UploadFileEnum;
import com.sj.model.model.UploadResult;
import com.sj.repository.util.FileUtil;

public class BaseController {
	@Autowired
	private AsyncWriteFileService writeFileService;

	ViewPage caculatePage(Page page) {
		int current = page.getNumber();
		int pages = page.getTotalPages();
		if (pages <= 7) {
			// return begin and total pages;
			return new ViewPage(1, pages);
		} else {
			if (current <= 4) {
				int end = pages >= 7 ? 7 : pages;
				return new ViewPage(1, end);
			} else if (current >= pages - 3 && current <= pages) {
				int begin = pages - 7;
				int end = pages;
				// return begin end
				return new ViewPage(begin, end);
			} else {
				int begin = current - 3;
				int end = current + 3 >= pages ? pages : current + 3;
				return new ViewPage(begin, end);
			}
		}
	}

	private class ViewPage {
		private int begin;
		private int end;

		public ViewPage(int begin, int end) {
			this.begin = begin;
			this.end = end;
		}

		public int getBegin() {
			return begin;
		}

		public void setBegin(int begin) {
			this.begin = begin;
		}

		public int getEnd() {
			return end;
		}

		public void setEnd(int end) {
			this.end = end;
		}

	}

	public UploadResult upload(MultipartFile file, HttpServletRequest request) {
		String contentType = file.getContentType();
		String fileName = FileUtil.getFileName(contentType);
		writeFileService.writeToFile(file, UploadFileEnum.IMAGE, fileName);
		return writeFileService.getResult(file, UploadFileEnum.IMAGE, fileName);
	}
}
