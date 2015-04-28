package com.sj.web.controller;

import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.Product;
import com.sj.model.model.Review;
import com.sj.repository.service.ReviewService;

@Controller
public class ReviewController {
	@Autowired
	private ReviewService reviewService;

	@RequestMapping(value = "/products/{productId}/reviews", method = RequestMethod.GET)
	@ResponseBody
	private String list(@PathVariable("productId") Long productId,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size) {
		Product product = new Product(productId);
		Page<Review> reviews = reviewService.findByProduct(product,
				new PageRequest(page - 1, size, Direction.DESC, "createdTime"));
		return convertJSONString(reviews.getContent().stream());
	}

	private String convertJSONString(Stream<Review> reviews) {
		JSONArray array = new JSONArray();
		reviews.map(r -> {
			JSONObject object = new JSONObject();
			object.put("content", r.getContent());
			object.put("userId", r.getCreatedBy().getId());
			object.put("userName", r.getCreatedBy().getName());
			return object;
		}).forEach(i -> array.put(i));
		return array.toString();
	}
}
