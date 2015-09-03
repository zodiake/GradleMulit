package com.sj.web.controller;

import java.util.Calendar;
import java.util.HashSet;
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
import com.sj.web.annotation.SecurityUser;
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
	private String addCartLine(@ModelAttribute CartLine cartLine,
			HttpSession httpSession,@SecurityUser SiteUser user) {
		if (!userContext.isLogin())
			return "{\"data\":\"login\"}";
		if (!userContext.hasRole(new SimpleGrantedAuthority("ROLE_COMMONUSER")))
			return "{\"data\":\"no authority\"}";

		Set<CartLine> lines = cartLineService.findByUser(user.getId());
		if (lines != null) {
			for (CartLine cart : lines) {
				if (cartLine.getProductId().equals(cart.getProductId())) {
					int num = cart.getNumber();
					cartLineService.updateNumber(user.getId(), cart.getId(), num+cartLine.getNumber());
					cart.setNumber(num+cartLine.getNumber());
					httpSession.setAttribute("cartLines", lines);
					return "{\"data\":\"addone\"}";
				}
			}
		}else{
			lines = new HashSet<CartLine>();
		}
		Product p = productService.findOne(cartLine.getProductId());
		cartLine = new CartLine(p, cartLine.getNumber());
		cartLine.setId(Calendar.getInstance().getTime().getTime());
		
		cartLineService.save(user.getId(), cartLine);
		lines.add(cartLine);
		
		httpSession.setAttribute("cartLines", lines);
		return "{\"image\":\""+p.getCoverImg()+"\",\"name\":\""+p.getName()+"\",\"price\":\""+p.getPrice()+"\"}";
	}
	

	@RequestMapping(value = "/user/cart/{productId}", method = RequestMethod.DELETE)
	@ResponseBody
	private String removeCartLine(@PathVariable("productId") Long productId,HttpSession session,@SecurityUser SiteUser user) {
		Set<CartLine> lines = (Set<CartLine>) session.getAttribute("cartLines");
		for (CartLine cartLine : lines) {
			if(cartLine.getProductId().equals(productId)){
				cartLineService.remove(user.getId(), cartLine.getId());
				lines.remove(cartLine);
				break;
			}
		}
		session.setAttribute("cartLines", lines);
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

	@RequestMapping(value = "/user/cart/{cartLineId}/{check}", method = RequestMethod.PUT, params = "check")
	@ResponseBody
	private String updateCartLineCheck(
			@PathVariable(value = "cartLineId") Long cartLineId,
			@PathVariable(value = "check") Boolean check) {
		if (!userContext.isLogin())
			return "fail";
		SiteUser user = userContext.getCurrentUser();
		cartLineService.updateCheck(user.getId(), cartLineId, check.toString());
		return "success";
	}
	@RequestMapping(value = "/user/cart/all", method = RequestMethod.PUT, params = "check")
	@ResponseBody
	private String updateAllCartLineCheck(){
		if (!userContext.isLogin())
			return "fail";
		SiteUser user = userContext.getCurrentUser();
		Set<CartLine> lines = cartLineService.findByUser(user.getId());
		for (CartLine cartLine : lines) {
			cartLineService.updateCheck(user.getId(), cartLine.getId(), "true");
		}
		return "success";
	}

	@RequestMapping(value = "/user/cart", method = RequestMethod.GET)
	private String list(Model uiModel) {
		SiteUser user = userContext.getCurrentUser();
		Set<CartLine> lists = cartLineService.findByUser(user.getId());
		uiModel.addAttribute("list", lists);
		uiModel.addAttribute("pc", new ProductCategory());
		return LIST;
	}
}
