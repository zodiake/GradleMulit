package com.sj.web.controller;

import java.util.stream.Stream;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.Product;
import com.sj.model.model.Review;
import com.sj.model.model.SiteUser;
import com.sj.repository.service.ReviewService;
import com.sj.web.security.UserContext;

@Controller
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private UserContext userContext;

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

	@RequestMapping(value = "/products/{productId}/reviews", method = RequestMethod.POST)
	@ResponseBody
	private String add(@ModelAttribute("review") Review review,
			@PathVariable("productId") Long productId) {
		System.out.println("review----------------"+review.getContent());
		if (!userContext.isLogin())
			return "login";
		if("".equals(review.getContent().trim()))
			return "content is null";
		Product p = new Product(productId);
		review.setProduct(p);
		SiteUser user = userContext.getCurrentUser();
		review.setCreatedBy(new SiteUser(user.getId()));
		reviewService.save(review);
		return user.getName();
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
