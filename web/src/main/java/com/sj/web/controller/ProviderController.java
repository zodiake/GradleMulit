package com.sj.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.model.model.Product;
import com.sj.model.model.Provider;
import com.sj.model.model.SiteUser;
import com.sj.repository.service.ProductService;
import com.sj.repository.service.SiteUserService;
import com.sj.web.security.UserContext;

@Controller
public class ProviderController extends BaseController<Provider> {
	@Autowired
	private SiteUserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserContext userContext;

	private final String USERPRODUCTS = "user/products";

	@RequestMapping(value = "/provider/products", method = RequestMethod.GET)
	public String productLists(Model uiModel,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size) {
		SiteUser user = userContext.getCurrentUser();
		Page<Product> products = productService.findByUsers(
				new Provider(user.getId()), new PageRequest(page - 1, size,
						Direction.DESC, "createdTime"));
		uiModel.addAttribute("lists",products);
		return USERPRODUCTS;
	}
}
