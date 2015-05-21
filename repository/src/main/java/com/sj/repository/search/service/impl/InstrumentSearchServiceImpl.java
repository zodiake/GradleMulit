package com.sj.repository.search.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.index.query.BoolFilterBuilder;
import org.elasticsearch.index.query.FilteredQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder.Operator;
import org.elasticsearch.index.query.RangeFilterBuilder;
import org.elasticsearch.index.query.TermFilterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.sj.repository.repository.InstrumentSearchRepository;
import com.sj.repository.search.model.InstrumentSearch;
import com.sj.repository.search.model.InstrumentSearchOption;
import com.sj.repository.search.service.InstrumentSearchService;

@Service
public class InstrumentSearchServiceImpl implements InstrumentSearchService {
	@Autowired
	private InstrumentSearchRepository repository;
	@Autowired
	private ElasticsearchTemplate searchTemplate;

	@Override
	public Page<InstrumentSearch> findByBrandAndPriceBetween(String brand,
			double min, double max, Pageable pageable) {
		return repository.findByBrandAndPriceBetween(brand, min, max, pageable);
	}

	@Override
	public void save(InstrumentSearch search) {
		repository.save(search);
	}

	@Override
	public Page<InstrumentSearch> findByOption(InstrumentSearchOption option,
			Pageable pageable) {
		Field[] fields = option.getClass().getDeclaredFields();
		List<Field> options = filterNullValue(option, fields);
		if (options.size() > 0) {
			SearchQuery query;
			try {
				query = buidSearchQuery(option, options, pageable);
				return searchTemplate.queryForPage(query,
						InstrumentSearch.class);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} else if (options.size() == 1) {
			for (Field f : options) {
				if (f.getName().equals("title")) {
					String title;
					try {
						title = (String) f.get(option);
						return repository.findByTitle(title, pageable);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return repository.findAll(pageable);
	}

	public List<Field> filterNullValue(InstrumentSearchOption option,
			Field[] fields) {
		return Arrays.stream(fields).filter(f -> {
			if (!Modifier.isPublic(f.getModifiers())) {
				f.setAccessible(true);
			}
			try {
				return f.get(option) != null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}).collect(Collectors.toList());
	}

	public SearchQuery buidSearchQuery(InstrumentSearchOption option,
			List<Field> fields, Pageable pageable)
			throws IllegalArgumentException, IllegalAccessException {
		MatchQueryBuilder queryBuilder = null;
		SearchQuery query = null;
		BoolFilterBuilder boolFilterBuilder = new BoolFilterBuilder();
		String title = null;
		for (Field f : fields) {
			if (!Modifier.isPublic(f.getModifiers())) {
				f.setAccessible(true);
			}
			switch (f.getName()) {
			case "to":
				RangeFilterBuilder toRange = new RangeFilterBuilder("price");
				toRange.lte((Float) f.get(option));
				boolFilterBuilder.must(toRange);
				break;
			case "from":
				RangeFilterBuilder fromRange = new RangeFilterBuilder("price");
				fromRange.gte((Float) f.get(option));
				boolFilterBuilder.must(fromRange);
				break;
			case "brand":
			case "secondCategory":
			case "thirdCategory":
				TermFilterBuilder brandTerm = new TermFilterBuilder(
						f.getName(), (String) f.get(option));
				boolFilterBuilder.must(brandTerm);
				break;
			case "title":
				title = (String) f.get(option);
				queryBuilder = new MatchQueryBuilder("title", title);
				queryBuilder.operator(Operator.AND);
				break;
			}
		}
		if (queryBuilder == null && !boolFilterBuilder.hasClauses()) {
			MatchAllQueryBuilder builder = new MatchAllQueryBuilder();
			query = new NativeSearchQuery(builder);
			query.setPageable(pageable);
			return query;
		} else if (queryBuilder == null) {
			MatchAllQueryBuilder match = new MatchAllQueryBuilder();
			FilteredQueryBuilder builder = new FilteredQueryBuilder(match,
					boolFilterBuilder);
			query = new NativeSearchQuery(builder);
			query.setPageable(pageable);
			return query;
		} else if (queryBuilder != null && !boolFilterBuilder.hasClauses()) {
			MatchQueryBuilder builder = new MatchQueryBuilder("title", title);
			builder.operator(Operator.AND);
			query = new NativeSearchQuery(builder);
			query.setPageable(pageable);
			return query;
		}
		FilteredQueryBuilder builder = new FilteredQueryBuilder(queryBuilder,
				boolFilterBuilder);
		query = new NativeSearchQuery(builder);
		query.setPageable(pageable);
		return query;
	}
}