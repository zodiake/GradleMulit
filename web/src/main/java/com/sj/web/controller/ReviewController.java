package com.sj.web.controller;

import java.util.Calendar;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.Product;
import com.sj.model.model.Review;
import com.sj.model.model.SiteUser;
import com.sj.repository.service.ProductService;
import com.sj.repository.service.ReviewService;
import com.sj.web.exception.ProductNotFoundException;
import com.sj.web.security.UserContext;

@Controller
public class ReviewController extends BaseController<Review>{
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private UserContext userContext;
	@Autowired
	private ProductService productService;
	@Autowired
	private StringRedisTemplate template;
	
	@RequestMapping(value = "/products/{productId}/reviews", method = RequestMethod.GET)
	private String list(@PathVariable("productId") Long productId,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "15") int size,Model uiModel) {
		Product product = productService.findOne(productId);
		if(product==null)
			throw new ProductNotFoundException();
		Page<Review> reviews = reviewService.findByProduct(product,
				new PageRequest(page , size, Direction.DESC, "createdTime"));
		
		ViewPage viewpage = caculatePage(reviews);
		viewpage.setHref("/products/"+productId+"/reviews");
		viewpage.setCurrent(reviews.getNumber());
		
		uiModel.addAttribute("reviewPage", reviews);
		uiModel.addAttribute("viewpage", viewpage);
		uiModel.addAttribute("nowTime", Calendar.getInstance().getTime().getTime());
		return "product/review";
	}

	@RequestMapping(value = "/products/{productId}/reviews", method = RequestMethod.POST)
	@ResponseBody
	private String add(@ModelAttribute("review") Review review,
			@PathVariable("productId") Long productId) {
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