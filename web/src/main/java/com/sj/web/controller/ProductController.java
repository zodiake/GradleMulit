package com.sj.web.controller;

import static com.sj.repository.util.RedisConstant.COLLECTIONCOUNT;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.model.model.Brand;
import com.sj.model.model.Product;
import com.sj.repository.search.model.ModelSearchOption;
import com.sj.repository.service.ProductService;
import com.sj.repository.service.ReviewService;
import com.sj.web.exception.ProductNotFoundException;
import com.sj.web.security.UserContext;

@Controller
public class ProductController extends BaseController<Product> {
	@Autowired
	private ProductService productService;
	@Autowired
	private UserContext userContext;
	@Autowired
	private StringRedisTemplate template;
	@Autowired
	private ReviewService reviewService;
	
	private final String DETAIL = "product/product";

	@RequestMapping(value = "/product/{id}", method = RequestMethod.POST)
	public void addCount(@PathVariable(value = "id") Long id) {
		productService.addViewCount(id);
	}

	@RequestMapping(value = "/models/_search", method = RequestMethod.GET)
	public String findBrandByName(Model uiModel,
			@ModelAttribute ModelSearchOption option,
			@PageableDefault(page = 0, size = 12) Pageable pageable) {
		Page<Product> productPage = productService.findBySearchModel(option,
				pageable);

		Map<String, String> map = productService.buildMap(option);

		ViewPage viewPage = caculatePage(productPage);
		viewPage.setHref("/models/_search");
		viewPage.setOption(map);
		viewPage.setCurrent(productPage.getNumber());

		uiModel.addAttribute("viewpage", viewPage);
		uiModel.addAttribute("page", productPage);
		uiModel.addAttribute("option", option);
		uiModel.addAttribute("action", "/models/_search");
		uiModel.addAttribute("field", "型号");
		return "search/model/products";
	}

	@RequestMapping(value = "/products/brand/{id}", method = RequestMethod.GET)
	public String findByBrandId(@PathVariable("id") Long brandId,
			Model uiModel,
			@PageableDefault(page = 0, size = 12) Pageable pageable) {
		Page<Product> productPage = productService.findByBrand(pageable, new Brand(brandId));
		
		ViewPage viewPage = caculatePage(productPage);
		viewPage.setHref("/products/brand/" + brandId);
		viewPage.setCurrent(productPage.getNumber());
		
		uiModel.addAttribute("viewpage", viewPage);
		uiModel.addAttribute("page", productPage);
		return "search/brand/products";
	}
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public String view(Model uiModel, @PathVariable(value = "id") Long id) {
		Product product = new Product();
		if(userContext.isLogin()){
			 product = productService.findUpOneUserIsLogin(id, userContext.getCurrentUser());
		}else{
			 product = productService.findUpOne(id);
		}
		
		if (product == null)
			throw new ProductNotFoundException();
		String collectionCount = template.opsForValue().get(COLLECTIONCOUNT + id.toString());
		if(collectionCount!=null)
			product.setCollectionCount(Long.valueOf(template.opsForValue().get(COLLECTIONCOUNT + id.toString())));
		else
			product.setCollectionCount(0l);
		productService.addViewCount(id);

		long reviewCount = reviewService.findCountByProduct(product);
		
		uiModel.addAttribute("product", product);
		uiModel.addAttribute("reviewCount", reviewCount);
		uiModel.addAttribute("pc", product.getFirstCategory());
		return DETAIL;
	}
}