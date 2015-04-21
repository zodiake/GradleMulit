package com.sj.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping(value = "/user/preferProduct/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String addPrefer(@PathVariable("id") int id) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication.getPrincipal() instanceof String) {
			String principal = (String) authentication.getPrincipal();
			if (principal.equals("anonymousUser")) {
				return "login";
			}
		}
		SiteUser user = userContext.getCurrnetUser();
		Product product = productService.findOne(id);
		if (product == null)
			throw new ProductNotFoundException();
		if (preferProductService.isDuplicateProduct(user, product))
			return "duplicate";
		PreferProduct preferProduct = new PreferProduct(user, product);
		preferProductService.save(preferProduct);
		return "success";
	}

}