package com.sj.repository.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

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
		try {
			this.options = initOption(option);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public ViewPage(Page<T> page, String href, String key, String value) {
		init(page, href);
		this.options = key + "=" + value;
	}

	private void init(Page<T> page, String href) {
		int pages = page.getTotalPages();
		this.current = page.getNumber();
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
		this.href = href;
	}

	private String initOption(Object object) throws IllegalArgumentException,
			IllegalAccessException {
		StringBuilder build = new StringBuilder();
		Class cls = object.getClass();
		if (cls != null) {
			Field[] fields = cls.getDeclaredFields();
			for (Field f : fields) {
				if (!Modifier.isPublic(f.getModifiers())) {
					f.setAccessible(true);
				}
				Object val = f.get(object);
				if (val != null)
					build.append(f.getName()).append("=").append(val)
							.append(",");
			}
		}
		if (build.length() == 0)
			return build.toString();
		else
			return build.substring(0, build.length() - 1);
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