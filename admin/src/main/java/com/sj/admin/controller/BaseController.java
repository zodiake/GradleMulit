package com.sj.admin.controller;

import org.springframework.data.domain.Page;

public class BaseController {
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
}
