package com.sj.repository.search.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.elasticsearch.index.query.BoolFilterBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FilteredQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder.Operator;
import org.elasticsearch.index.query.TermFilterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.sj.model.model.Subject;
import com.sj.repository.repository.SubjectSearchRepository;
import com.sj.repository.search.model.SubjectSearch;
import com.sj.repository.search.model.SubjectSearchOption;
import com.sj.repository.search.service.SubjectSearchService;

@Service
public class SubjectSearchServiceImpl implements SubjectSearchService {
	@Autowired
	private SubjectSearchRepository repository;
	@Autowired
	protected ElasticsearchTemplate searchTemplate;

	@Override
	public void save(SubjectSearch search) {
		repository.save(search);
	}

	@Override
	public Page<SubjectSearch> findByOption(SubjectSearchOption option,
			Pageable pageable) {
		Field[] fields = option.getClass().getDeclaredFields();
		List<Field> options = filterNullValue(option, fields);

		if (options.size() > 0) {
			SearchQuery query;
			try {
				query = buidSearchQuery(option, options, pageable);
				return searchTemplate.queryForPage(query, SubjectSearch.class);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return repository.findAll(pageable);
	}

	@Override
	public Map<String, String> buildMap(SubjectSearchOption option) {
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

	public SearchQuery buidSearchQuery(SubjectSearchOption option,
			List<Field> fields, Pageable pageable)
			throws IllegalArgumentException, IllegalAccessException {
		MatchQueryBuilder queryBuilder = null;
		SearchQuery query = null;
		BoolFilterBuilder boolFilterBuilder = new BoolFilterBuilder();
		List<MatchQueryBuilder> mustClauses = new ArrayList<>();
		String v = null;
		for (Field f : fields) {
			Object value = f.get(option);
			if (!Modifier.isPublic(f.getModifiers())) {
				f.setAccessible(true);
			}
			switch (f.getName()) {
			case "category":
				TermFilterBuilder categoryTerm = new TermFilterBuilder(
						"category", value);
				boolFilterBuilder.must(categoryTerm);
				break;
			case "title":
				v = (String) value;
				queryBuilder = new MatchQueryBuilder("title", v);
				queryBuilder.operator(Operator.AND);
				mustClauses.add(queryBuilder);
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
			BoolQueryBuilder builder = new BoolQueryBuilder();
			mustClauses.forEach(i -> builder.must(i));
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

	public List<Field> filterNullValue(SubjectSearchOption option,
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
	public void update(Subject s) {
		SubjectSearch ss = repository.findOne(s.getId());
		ss.setCategory(s.getCategory().getName());
		ss.setImage(s.getImage());
		ss.setSummary(s.getSummary());
		ss.setTitle(s.getName());
		repository.save(ss);
	}

	@Override
	public void delete(SubjectSearch search) {
		repository.delete(search);
	}
}
