package com.sj.web.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.CartLine;
import com.sj.model.model.SiteUser;
import com.sj.repository.service.CartLineService;
import com.sj.repository.service.ProductService;
import com.sj.web.security.UserContext;
import com.sj.web.util.AuthorityUtil;

@Controller
public class CartController {
	@Autowired
	private UserContext userContext;
	@Autowired
	private ProductService productService;
	@Autowired
	private CartLineService cartLineService;

	private final String LIST = "cart/list";

	@RequestMapping(value = "/ajax/cart", method = RequestMethod.POST)
	@ResponseBody
	private String addCartLine(@ModelAttribute CartLine cartLine) {
		if (!userContext.isLogin())
			return "login";
		SiteUser user = userContext.getCurrentUser();
		if (!userContext.hasRole(new SimpleGrantedAuthority("ROLE_COMMONUSER")))
			return "no authority";
		cartLineService.save(user.getId(), cartLine);
		return "success";
	}

	@RequestMapping(value = "/user/cart", method = RequestMethod.DELETE)
	@ResponseBody
	private String removeCartLine(@RequestParam("id") Long productId) {
		SiteUser user = userContext.getCurrentUser();
		cartLineService.remove(user.getId(), productId);
		return "success";
	}

	@RequestMapping(value = "/user/cart/{cartLineId}", method = RequestMethod.PUT)
	@ResponseBody
	private String updateCartLineNumber(
			@PathVariable(value = "cartLineId") Long cartLineId,
			@RequestParam("number") int number) {
		if (!userContext.isLogin())
			return "fail";
		SiteUser user = userContext.getCurrentUser();
		cartLineService.updateNumber(user.getId(), cartLineId, number);
		return "success";
	}

	@RequestMapping(value = "/user/cart", method = RequestMethod.GET)
	private String list(Model uiModel) {
		SiteUser user = userContext.getCurrentUser();
		Set<CartLine> lists = cartLineService.findByUser(user.getId());
		uiModel.addAttribute("list", lists);
		return LIST;
	}
}
