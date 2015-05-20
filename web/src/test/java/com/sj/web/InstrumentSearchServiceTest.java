package com.sj.web;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import com.sj.repository.search.model.InstrumentSearchOption;
import com.sj.repository.search.service.impl.InstrumentSearchServiceImpl;

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
		InstrumentSearchServiceImpl impl = new InstrumentSearchServiceImpl();
		InstrumentSearchOption option = new InstrumentSearchOption();
		option.setBrand("brand");
		option.setFrom(2f);
		List<Field> array = impl.filterNullValue(option, option.getClass()
				.getDeclaredFields());
		array.stream().forEach(System.out::println);
		assertEquals(2,array.size());
		SearchQuery query = impl.buidSearchQuery(option, array, pageable);
		assertEquals("1", query.getQuery().toString());
	}
}
