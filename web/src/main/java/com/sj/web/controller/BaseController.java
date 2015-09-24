package com.sj.web.controller;

import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class BaseController<T> {
	ViewPage caculatePage(Page<T> page) {
		int current = page.getNumber()+1;
		int pages = page.getTotalPages();
		if (pages == 0) {
			return new ViewPage(1, 1, 1);
		}
		if (pages <= 7) {
			// return begin and total pages;
			return new ViewPage(1, pages, current );
		} else {
			if (current <= 4) {
				int end = pages >= 7 ? 7 : pages;
				return new ViewPage(1, end, current);
			} else if (current >= pages - 3 && current <= pages) {
				int begin = pages - 6;
				int end = pages;
				// return begin end
				return new ViewPage(begin, end, current );
			} else {
				int begin = current - 3;
				int end = current + 3 >= pages ? pages : current + 3;
				return new ViewPage(begin, end, current );
			}
		}
	}

	protected class ViewPage {
		private int begin;
		private int end;
		private int current;
		private String option = "";
		private String href;

		public ViewPage(int begin, int end, int current) {
			this.begin = begin;
			this.end = end;
			this.current = current;
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

		public void setOption(Map<String, String> map) {
			if (map.size() > 0) {
				for (Entry e : map.entrySet()) {
					option += e.getKey() + "=" + e.getValue() + ",";
					option.substring(0, option.lastIndexOf(","));
				}
			} else {
				option = "";
			}
		}

		public String getOption() {
			return option;
		}

		public String getHref() {
			return href;
		}

		public void setHref(String href) {
			this.href = href;
		}

		public int getCurrent() {
			return current;
		}

		public void setCurrent(int current) {
			this.current = current;
		}
	}

	protected String getFileName(MultipartFile file) {
		Calendar c = Calendar.getInstance();
		String fileName = String.valueOf(c.hashCode())
				+ StringUtils.trimAllWhitespace(file.getOriginalFilename());
		return fileName;
	}
}