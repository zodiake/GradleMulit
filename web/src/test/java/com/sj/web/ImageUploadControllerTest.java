package com.sj.web;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.sj.model.type.OriginalEnum;
import com.sj.repository.util.ViewPage;
import com.sj.web.util.InstrumentSearch;

public class ImageUploadControllerTest {
	@Test
	public void test() {
		assertEquals(1, 1);
	}

	@Test
	public void testSearch() {
		List<String> lists = new ArrayList<>();
		lists.add("a");
		lists.add("b");
		lists.add("a");
		lists.add("a");
		lists.add("a");
		lists.add("a");
		Page<String> page = new PageImpl<String>(lists);
		InstrumentSearch i = new InstrumentSearch();
		i.setName("name");
		i.setBrand("brand");
		ViewPage viewpage = new ViewPage(page, "/admin", i);
		assertEquals("brand=brand,name=name", viewpage.getOptions());
	}

	@Test
	public void testReflection() {
		List<String> lists = new ArrayList<>();
		lists.add("a");
		lists.add("b");
		lists.add("a");
		lists.add("a");
		lists.add("a");
		lists.add("a");
		Page<String> page = new PageImpl<String>(lists);
		InstrumentSearch i = new InstrumentSearch();
		i.setName("name");
		ViewPage viewpage = new ViewPage(page, "/admin", i);
		assertEquals("name=name", viewpage.getOptions());
	}

	@Test
	public void testEnum() {
		OriginalEnum en = OriginalEnum.PASS;
		assertEquals("", en.toString(), "in");
	}
}
