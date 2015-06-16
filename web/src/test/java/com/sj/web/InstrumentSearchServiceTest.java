package com.sj.web;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import com.sj.repository.search.model.ProductSearchOption;
import com.sj.repository.search.service.impl.ProductSearchServiceImpl;

public class InstrumentSearchServiceTest {
	@Mock
	private static Pageable pageable;

	@BeforeClass
	public static void setup() {
		pageable = Mockito.mock(Pageable.class);
		Mockito.when(pageable.getPageNumber()).thenReturn(0);
	}

	@Test
	public void testsearchByOption() throws IllegalArgumentException,
			IllegalAccessException, SecurityException {
		ProductSearchServiceImpl impl = new ProductSearchServiceImpl();
		ProductSearchOption option = new ProductSearchOption();
		option.setBrand("brand");
		option.setFrom(2f);
		option.setTitle("title");
		List<Field> array = impl.filterNullValue(option, option.getClass()
				.getDeclaredFields());
		assertEquals(3, array.size());
		SearchQuery query = impl.buidSearchQuery(option, array, pageable);
		assertEquals("1", query.getQuery().toString());
	}

	@Test
	public void testsearchWithOutTitle() throws IllegalArgumentException,
			IllegalAccessException, SecurityException {
		ProductSearchServiceImpl impl = new ProductSearchServiceImpl();
		ProductSearchOption option = new ProductSearchOption();
		option.setBrand("brand");
		option.setFrom(2f);
		List<Field> array = impl.filterNullValue(option, option.getClass()
				.getDeclaredFields());
		assertEquals(2, array.size());
		SearchQuery query = impl.buidSearchQuery(option, array, pageable);
		assertEquals("1", query.getQuery().toString());
	}

	@Test
	public void testsearchOnlyWithTitle() throws IllegalArgumentException,
			IllegalAccessException, SecurityException {
		ProductSearchServiceImpl impl = new ProductSearchServiceImpl();
		ProductSearchOption option = new ProductSearchOption();
		option.setTitle("title");
		List<Field> array = impl.filterNullValue(option, option.getClass()
				.getDeclaredFields());
		assertEquals(1, array.size());
		SearchQuery query = impl.buidSearchQuery(option, array, pageable);
		assertEquals("1", query.getQuery().toString());
	}

	@Test
	public void testsearchMatchAll() throws IllegalArgumentException,
			IllegalAccessException, SecurityException {
		ProductSearchServiceImpl impl = new ProductSearchServiceImpl();
		ProductSearchOption option = new ProductSearchOption();
		List<Field> array = impl.filterNullValue(option, option.getClass()
				.getDeclaredFields());
		assertEquals(0, array.size());
		SearchQuery query = impl.buidSearchQuery(option, array, pageable);
		String assertString = "{\"match_all\":{}}";
		assertEquals(assertString, query.getQuery().toString());
	}
}
