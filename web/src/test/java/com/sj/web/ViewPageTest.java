package com.sj.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;

import com.sj.model.model.Instrument;
import com.sj.repository.service.Impl.InstrumentServiceImpl;
import com.sj.repository.util.ViewPage;
import com.sj.web.util.InstrumentSearch;

public class ViewPageTest {
	@Mock
	private static InstrumentServiceImpl instrumentService;
	@Mock
	private static Page<Instrument> instruments;

	@BeforeClass
	public static void setup() {
		instrumentService = mock(InstrumentServiceImpl.class);
		instruments = mock(Page.class);
	}

	@Test
	public void testFirstPage() {
		when(instruments.getNumber()).thenReturn(1);
		when(instruments.getTotalPages()).thenReturn(20);
		InstrumentSearch search = new InstrumentSearch();
		ViewPage<Instrument> view = new ViewPage<Instrument>(instruments,
				"/instruments", search);
		assertEquals(1, view.getBegin());
		assertEquals(7, view.getEnd());
	}

	@Test
	public void testLessThan4() {
		when(instruments.getNumber()).thenReturn(3);
		when(instruments.getTotalPages()).thenReturn(20);
		InstrumentSearch search = new InstrumentSearch();
		ViewPage<Instrument> view = new ViewPage<Instrument>(instruments,
				"/instruments", search);
		assertEquals(1, view.getBegin());
		assertEquals(7, view.getEnd());
	}

	@Test
	public void testGreaterThan4() {
		when(instruments.getNumber()).thenReturn(5);
		when(instruments.getTotalPages()).thenReturn(20);
		InstrumentSearch search = new InstrumentSearch();
		ViewPage<Instrument> view = new ViewPage<Instrument>(instruments,
				"/instruments", search);
		assertEquals(2, view.getBegin());
		assertEquals(8, view.getEnd());
	}

	@Test
	public void test3LessThanPages() {
		when(instruments.getNumber()).thenReturn(18);
		when(instruments.getTotalPages()).thenReturn(20);
		InstrumentSearch search = new InstrumentSearch();
		ViewPage<Instrument> view = new ViewPage<Instrument>(instruments,
				"/instruments", search);
		assertEquals(13, view.getBegin());
		assertEquals(20, view.getEnd());
	}
}