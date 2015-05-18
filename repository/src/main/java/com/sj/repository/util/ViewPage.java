package com.sj.repository.util;

import java.util.Map.Entry;
import java.util.Set;

import org.springframework.cglib.beans.BeanMap;
import org.springframework.data.domain.Page;

public class ViewPage<T> {
	private int begin;
	private int end;
	private int current;
	private String href;
	private String options;

	public ViewPage(Page<T> page, String href) {
		init(page, href);
	}

	public ViewPage(Page<T> page, String href, Object option) {
		init(page, href);
		this.options = initOption(option);
	}

	public ViewPage(Page<T> page, String href, String key, String value) {
		init(page, href);
		this.options = key + "=" + value;
	}

	private void init(Page<T> page, String href) {
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
		this.href = href;
	}

	private String initOption(Object object) {
		BeanMap map = BeanMap.create(object);
		Set<Entry<Object, Object>> t = map.entrySet();
		StringBuilder builder = new StringBuilder();
		for (Entry<Object, Object> e : t) {
			builder.append(e.getKey()).append("=").append(e.getValue())
					.append(",");
		}
		return builder.substring(0, builder.length() - 1);
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

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}
}