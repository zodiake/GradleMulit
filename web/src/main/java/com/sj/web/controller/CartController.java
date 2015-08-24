package com.sj.web.controller;

import java.util.Calendar;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.sj.model.model.Product;
import com.sj.model.model.ProductCategory;
import com.sj.model.model.SiteUser;
import com.sj.repository.service.CartLineService;
import com.sj.repository.service.CartService;
import com.sj.repository.service.ProductService;
import com.sj.web.security.UserContext;

@Controller
public class CartController {
	@Autowired
	private UserContext userContext;
	@Autowired
	private ProductService productService;
	@Autowired
	private CartLineService cartLineService;
	@Autowired
	private CartService cartService;

	private final String LIST = "user/common/cart";

	@RequestMapping(value = "/ajax/cart", method = RequestMethod.POST)
	@ResponseBody
	private String addCartLine(@ModelAttribute CartLine cartLine,HttpSession httpSession) {
		cartLine.setId(Calendar.getInstance().getTime().getTime());
		if (!userContext.isLogin())
			return "login";
		SiteUser user = userContext.getCurrentUser();
		if (!userContext.hasRole(new SimpleGrantedAuthority("ROLE_COMMONUSER")))
			return "no authority";
		Product p = productService.findOne(cartLine.getProductId());
		cartLine.setId(Calendar.getInstance().getTime().getTime());
		CartLine c = new CartLine(p, cartLine.getNumber());
		cartLineService.save(user.getId(), c);
		Set<CartLine> lines = (Set<CartLine>) httpSession.getAttribute("cartLines");
		lines.add(c);
		httpSession.setAttribute("cartLines", lines);
		return "success";
	}

	@RequestMapping(value = "/user/cart/{productId}", method = RequestMethod.DELETE)
	@ResponseBody
	private String removeCartLine(@PathVariable("productId") Long productId) {
		SiteUser user = userContext.getCurrentUser();
		cartLineService.remove(user.getId(), productId);
		return "success";
	}

	@RequestMapping(value = "/user/cart/{cartLineId}/{number}", method = RequestMethod.PUT)
	@ResponseBody
	private String updateCartLineNumber(
			@PathVariable(value = "cartLineId") Long cartLineId,
			@PathVariable(value = "number") Integer number) {
		if (!userContext.isLogin())
			return "fail";
		SiteUser user = userContext.getCurrentUser();
		cartLineService.updateNumber(user.getId(), cartLineId, number);
		return "success";
	}
	
	@RequestMapping(value = "/user/cart/{cartLineId}/{check}", method = RequestMethod.PUT,params="check")
	@ResponseBody
	private String updateCartLineCheck(
			@PathVariable(value = "cartLineId") Long cartLineId,
			@PathVariable(value = "check") Boolean check) {
		if (!userContext.isLogin())
			return "fail";
		System.out.println("check......."+check);
		SiteUser user = userContext.getCurrentUser();
		cartLineService.updateCheck(user.getId(), cartLineId, check.toString());
		return "success";
	}

	@RequestMapping(value = "/user/cart", method = RequestMethod.GET)
	private String list(Model uiModel) {
		SiteUser user = userContext.getCurrentUser();
		Set<CartLine> lists = cartLineService.findByUser(user.getId());
		for (CartLine cartLine : lists) {
			System.out.println(cartLine.getId()+"/"+cartLine.getCheck());
		}
		uiModel.addAttribute("list", lists);
		uiModel.addAttribute("pc", new ProductCategory());
		return LIST;
	}
}
