package com.sj.web.controller;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
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

	@RequestMapping(value = "/ajax/carts", method = RequestMethod.POST)
	@ResponseBody
	private String addCartLine(@ModelAttribute CartLine cartLine,HttpSession httpSession) {
		if (!userContext.isLogin())
			return "{\"data\":\"login\"}";
		if (!userContext.hasRole(new SimpleGrantedAuthority("ROLE_COMMONUSER")))
			return "{\"data\":\"no authority\"}";
		SiteUser user = userContext.getCurrentUser();
		Set<CartLine> lines = cartLineService.findByUser(user.getId());
		if (lines != null) {
			for (CartLine cart : lines) {
				if (cartLine.getProductId().equals(cart.getProductId())) {
					int num = cart.getNumber();
					cartLineService.updateNumber(user.getId(), cart.getId(),
							num + cartLine.getNumber());
					cart.setNumber(num + cartLine.getNumber());
					httpSession.setAttribute("cartLines", lines);
					return "{\"data\":\"addone\"}";
				}
			}
		} else {
			lines = new HashSet<CartLine>();
		}
		Product p = productService.findOne(cartLine.getProductId());
		cartLine = new CartLine(p, cartLine.getNumber());
		cartLine.setId(Calendar.getInstance().getTime().getTime());

		cartLineService.save(user.getId(), cartLine);
		lines.add(cartLine);

		httpSession.setAttribute("cartLines", lines);
		return "{\"image\":\"" + p.getCoverImg() + "\",\"name\":\""
				+ p.getName() + "\",\"price\":\"" + p.getPrice() + "\"}";
	}

	@RequestMapping(value = "/ajax/carts", method = RequestMethod.POST,params="multiple")
	@ResponseBody
	private String addCartLines(@RequestParam("productIds") String[] productIds, HttpSession session) {
		if (!userContext.isLogin())
			return "{\"data\":\"login\"}";
		if (!userContext.hasRole(new SimpleGrantedAuthority("ROLE_COMMONUSER")))
			return "{\"data\":\"no authority\"}";
		SiteUser user = userContext.getCurrentUser();
		Set<CartLine> lines = cartLineService.findByUser(user.getId());
		if (lines == null) {
			lines = new HashSet<CartLine>();
		}
		for (int i = 0; i < productIds.length; i++) {
			String productId = productIds[i];
			boolean bool = true;
			if (lines.size() != 0) {
				for (CartLine cartLine : lines) {
					if (productId.equals(cartLine.getProductId().toString())) {
						if(cartLine.getNumber()<999){
							cartLineService.updateNumber(user.getId(), cartLine.getId(), 1 + cartLine.getNumber());
							cartLine.setNumber(1 + cartLine.getNumber());
						}
						bool = false;
						break;
					}
				}
			}
			if (bool) {
				Product p = productService.findOne(Long.valueOf(productId));
				CartLine cartLine = new CartLine(p, 1);
				cartLine.setId(Calendar.getInstance().getTime().getTime());
				cartLineService.save(user.getId(), cartLine);
				lines.add(cartLine);
			}
		}
		session.setAttribute("cartLines", lines);
		return convertJSONString(lines);
	}

	private String convertJSONString(Set<CartLine> lines) {
		JSONArray array = new JSONArray();
		for (CartLine cartLine : lines) {
			JSONObject object = new JSONObject();
			object.put("productId", cartLine.getProductId());
			object.put("image", cartLine.getImage());
			object.put("name", cartLine.getName());
			object.put("price", cartLine.getPrice());
			object.put("num", cartLine.getNumber());
			array.put(object);
		}
		return array.toString();
	}

	@RequestMapping(value = "/user/carts/{productId}", method = RequestMethod.DELETE)
	@ResponseBody
	private String removeCartLine(@PathVariable("productId") Long productId,
			HttpSession session, @SecurityUser SiteUser user) {
		Set<CartLine> lines = cartLineService.findByUser(user.getId());
		for (CartLine cartLine : lines) {
			if (cartLine.getProductId().equals(productId)) {
				cartLineService.remove(user.getId(), cartLine.getId());
				lines.remove(cartLine);
				break;
			}
		}
		session.setAttribute("cartLines", lines);
		return "success";
	}
	@RequestMapping(value = "/user/carts", method = RequestMethod.DELETE)
	@ResponseBody
	private String removeCartLines(@RequestParam("productIds") String[] productIds,
			HttpSession session, @SecurityUser SiteUser user) {
		Set<CartLine> lines = cartLineService.findByUser(user.getId());
		for (int i = 0; i < productIds.length; i++) {
			for (CartLine cartLine : lines) {
				if (productIds[i].equals(cartLine.getId())) {
					cartLineService.remove(user.getId(), cartLine.getId());
					lines.remove(cartLine);
					break;
				}
			}
		}
		session.setAttribute("cartLines", lines);
		return "success";
	}

	@RequestMapping(value = "/user/carts/{cartLineId}/{number}", method = RequestMethod.PUT)
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

	@RequestMapping(value = "/user/carts/{cartLineId}/{check}", method = RequestMethod.PUT, params = "check")
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

	@RequestMapping(value = "/user/carts/all/{check}", method = RequestMethod.PUT, params = "check")
	@ResponseBody
	private String updateAllCartLineCheck(@PathVariable("check") String check) {
		if (!userContext.isLogin())
			return "fail";
		SiteUser user = userContext.getCurrentUser();
		Set<CartLine> lines = cartLineService.findByUser(user.getId());
		if ("1".equals(check)) {
			for (CartLine cartLine : lines) {
				cartLineService.updateCheck(user.getId(), cartLine.getId(),
						"true");
			}
		} else {
			for (CartLine cartLine : lines) {
				cartLineService.updateCheck(user.getId(), cartLine.getId(),
						"false");
			}
		}
		return "success";
	}

	@RequestMapping(value = "/user/carts", method = RequestMethod.GET)
	private String list(Model uiModel) {
		SiteUser user = userContext.getCurrentUser();
		Set<CartLine> lists = cartLineService.findByUser(user.getId());
		uiModel.addAttribute("list", lists);
		uiModel.addAttribute("pc", new ProductCategory());
		return LIST;
	}
}
