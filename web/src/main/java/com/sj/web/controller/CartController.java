package com.sj.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.Cart;
import com.sj.model.model.Product;
import com.sj.model.model.SiteUser;
import com.sj.repository.service.ProductService;
import com.sj.web.exception.ProductNotFoundException;
import com.sj.web.security.UserContext;

@Controller
public class CartController {
	@Autowired
	private UserContext userContext;
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/user/addCart", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	private String addCartLine(@RequestParam(value = "id") Long id,
			HttpSession session) {
		if (!userContext.isLogin())
			return "login";
		SiteUser user = userContext.getCurrnetUser();
		Product product = productService.findOne(id);
		if (product == null)
			throw new ProductNotFoundException();
		// todo if duplicate

		Cart cart;
		if (session.getAttribute("sj_cart") != null) {
			cart = (Cart) session.getAttribute("sj_cart");
		} else {
			cart = new Cart();
			session.setAttribute("sj_cart", cart);
		}

		return "success";
	}
}
