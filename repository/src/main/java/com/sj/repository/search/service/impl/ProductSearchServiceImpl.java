package com.sj.repository.search.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.sj.repository.repository.ProductSearchRepository;
import com.sj.repository.search.model.ProductSearch;
import com.sj.repository.search.model.ProductSearchOption;
import com.sj.repository.search.service.ProductSearchService;

@Service
public class ProductSearchServiceImpl implements ProductSearchService {
	@Autowired
	protected ElasticsearchTemplate searchTemplate;

	@Autowired
	protected ProductSearchRepository repository;

	@Override
	public Page<ProductSearch> findByOption(ProductSearchOption option,
			Pageable pageable) {
		Field[] fields = option.getClass().getDeclaredFields();
		List<Field> options = filterNullValue(option, fields);

		if (options.size() > 0) {
			SearchQuery query;
			try {
				query = buidSearchQuery(option, options, pageable);
				return searchTemplate.queryForPage(query, ProductSearch.class);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return repository.findAll(pageable);
	}

	@Override
	public Map<String, String> buildMap(ProductSearchOption option) {
		Field[] fields = option.getClass().getDeclaredFields();
		List<Field> options = filterNullValue(option, fields);
		Map<String, String> map = new HashMap<>();
		for (Field f : options) {
			Object value;
			try {
				value = f.get(option);
				map.put(f.getName(), String.valueOf(value));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	public SearchQuery buidSearchQuery(ProductSearchOption option,
			List<Field> fields, Pageable pageable)
			throws IllegalArgumentException, IllegalAccessException {
		MatchQueryBuilder queryBuilder = null;
		SearchQuery query = null;
		BoolFilterBuilder boolFilterBuilder = new BoolFilterBuilder();
		String title = null;
		for (Field f : fields) {
			Object value = f.get(option);
			if (!Modifier.isPublic(f.getModifiers())) {
				f.setAccessible(true);
			}
			switch (f.getName()) {
			case "to":
				RangeFilterBuilder toRange = new RangeFilterBuilder("price");
				toRange.lte(value);
				boolFilterBuilder.must(toRange);
				break;
			case "from":
				RangeFilterBuilder fromRange = new RangeFilterBuilder("price");
				fromRange.gte(value);
				boolFilterBuilder.must(fromRange);
				break;
			case "brand":
			case "secondCategory":
			case "thirdCategory":
				TermFilterBuilder brandTerm = new TermFilterBuilder(
						f.getName(), (String) value);
				boolFilterBuilder.must(brandTerm);
				break;
			case "original":
				TermFilterBuilder originalTerm = new TermFilterBuilder(
						f.getName(), (String) value);
				boolFilterBuilder.must(originalTerm);
				break;
			case "title":
				title = (String) value;
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

	public List<Field> filterNullValue(ProductSearchOption option,
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

	@Override
	public void save(ProductSearch product) {
		repository.save(product);
	}

	@Override
	public Long count(SearchQuery query) {
		return searchTemplate.count(query);
	}
}
