package com.sj.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.CommonUser;
import com.sj.model.model.PreferProduct;
import com.sj.model.model.Product;
import com.sj.model.model.SiteUser;
import com.sj.repository.service.PreferProductService;
import com.sj.repository.service.ProductService;
import com.sj.web.exception.ProductNotFoundException;
import com.sj.web.security.UserContext;

@Controller
public class PreferProductController {
	@Autowired
	private PreferProductService preferProductService;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserContext userContext;

	private final String PREFEREPRODUCTS = "user/prefereProducts";

	@RequestMapping(value = "/ajax/preferProduct/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String addPrefer(@PathVariable("id") Long id) {
		if (!userContext.isLogin())
			return "login";
		SiteUser user = userContext.getCurrentUser();
		if(!"ROLE_COMMONUSER".equals(user.getSiteAuthority())){
			return "no authority";
		}
		Product product = productService.findOne(id);
		if (product == null)
			throw new ProductNotFoundException();
		if (preferProductService.isDuplicateProduct(
				new CommonUser(user.getId()), product))
			return "duplicate";
		CommonUser u = new CommonUser(user.getId());
		PreferProduct preferProduct = new PreferProduct(u, product);
		preferProductService.save(preferProduct);
		return "success";
	}

	@RequestMapping(value = "/user/preferProducts", method = RequestMethod.GET)
	public String showPreferedProducts(Model uiModel,
			@PageableDefault(size = 15) Pageable pageable) {
		SiteUser user = userContext.getCurrentUser();
		Page<PreferProduct> lists = preferProductService.findByUser(
				new CommonUser(user.getId()), pageable);
		uiModel.addAttribute("lists", lists);
		return PREFEREPRODUCTS;
	}

	@RequestMapping(value = "/ajax/preferProduct/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String removePrefer(@PathVariable("id") Long id) {
		SiteUser user = userContext.getCurrentUser();
		Product product = new Product(id);
		preferProductService.deleteByUserAndProduct(
				new CommonUser(user.getId()), product);
		return "success";
	}
}