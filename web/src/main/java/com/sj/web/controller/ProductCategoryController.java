package com.sj.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.model.model.CommonUser;
import com.sj.model.model.PreferProduct;
import com.sj.model.model.Product;
import com.sj.model.model.ProductCategory;
import com.sj.model.model.SiteUser;
import com.sj.repository.service.PreferProductService;
import com.sj.repository.service.ProductCategoryService;
import com.sj.repository.service.ProductService;
import com.sj.web.exception.CategoryNotFoundException;
import com.sj.web.security.SiteUserContext;

@Controller
public class ProductCategoryController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService pcService;
	@Autowired
	private PreferProductService preferProductService;
	@Autowired
	private SiteUserContext userContext;

	@RequestMapping(value = "/productCategory/{parent}/{id}", method = RequestMethod.GET)
	public String findOne(@PathVariable("id") Long id, Model uiModel,
			@PathVariable("parent") String parent,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size) {
		ProductCategory pc = ProductCategory.getFirst(parent);
		if (pc == null)
			throw new CategoryNotFoundException();
		uiModel.addAttribute("pc", pc);

		ProductCategory child = pcService.findOneActivate(id);
		if (child == null)
			throw new CategoryNotFoundException();
		uiModel.addAttribute("child", child);

		Page<Product> pages = productService.findByCategory(child,
				new PageRequest(page - 1, size));
		uiModel.addAttribute("page", pages);
		List<Product> products = pages.getContent();
		boolean bool = userContext.isLogin();
		if (bool) {
			SiteUser user = userContext.getCurrentUser();
			List<PreferProduct> prefer = preferProductService
					.findByUser(new CommonUser(user.getId()));
			if (prefer != null && prefer.size() != 0) {
				for (Product product : products) {
					Long productId = product.getId();
					for (int i = 0; i < prefer.size(); i++) {
						if (product.getId().equals(
								prefer.get(i).getProduct().getId())) {
							product.setCollection(true);
							break;
						}
					}
				}
			}
		}
		uiModel.addAttribute("products", products);

		return "product/products";
	}

	@RequestMapping(value = "/productCategory/{name}", method = RequestMethod.GET)
	public String findByParent(@PathVariable("name") String name, Model uiModel) {
		ProductCategory pc = ProductCategory.getFirst(name);
		if (pc == null)
			throw new CategoryNotFoundException();
		List<ProductCategory> categories = pcService.findByParent(pc);
		uiModel.addAttribute("pc", pc);
		uiModel.addAttribute("categories", categories);
		return "/productcategory/productCategorys";
	}

}
