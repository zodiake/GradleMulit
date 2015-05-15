package com.sj.repository.util;

import org.springframework.data.domain.Page;

public class ViewPage<T> {
	private int begin;
	private int end;
	private int current;

	public ViewPage(Page<T> page) {
		int pages = page.getTotalPages();
		int begin, end;
		if (pages <= 7) {
			begin = 1;
			end = pages;
		} else {
			if (current <= 4) {
				begin = 1;
				end = pages >= 7 ? 7 : pages;
			} else if (current >= pages - 3 && current <= pages) {
				begin = pages - 7;
				end = pages;
			} else {
				begin = current - 3;
				end = current + 3 >= pages ? pages : current + 3;
			}
		}
		this.begin = begin;
		this.end = end;
		this.current = page.getNumber();
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

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}
}