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

import com.sj.model.model.PreferProduct;
import com.sj.model.model.SiteUser;
import com.sj.repository.service.PreferProductService;
import com.sj.repository.service.SiteUserService;
import com.sj.web.security.UserContext;

@Controller
public class SiteUserController extends BaseController {
	@Autowired
	private SiteUserService userService;
	@Autowired
	private PreferProductService preferProductService;
	@Autowired
	private UserContext userContext;

	private final String PREFEREPRODUCTS = "user/prefereProducts";

	@RequestMapping(value = "/user/preferProducts", method = RequestMethod.GET)
	public String showPreferedProducts(Model uiModel,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size) {
		SiteUser user = userContext.getCurrnetUser();
		Page<PreferProduct> lists=preferProductService.findByUser(user, new PageRequest(page-1, size,
				Direction.DESC, "dateAdded"));
		uiModel.addAttribute("lists", lists);
		return PREFEREPRODUCTS;
	}
}
