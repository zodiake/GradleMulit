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
import com.sj.model.type.ActivateEnum;
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

	@RequestMapping(value = "/productCategory/{parent}/{second}/{third}", method = RequestMethod.GET)
	public String findByThird(@PathVariable("third") String third,
			Model uiModel, @PathVariable("parent") String parent,
			@PathVariable("second") String second,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size) {
		ProductCategory thirdCategory = pcService.findByName(third,
				ActivateEnum.ACTIVATE);
		if (thirdCategory == null
				|| thirdCategory.getParent() == null
				|| !thirdCategory.getParent().getName().equals(second)
				|| thirdCategory.getParent().getParent() == null
				|| !thirdCategory.getParent().getParent().getName()
						.equals(parent)) {
			throw new CategoryNotFoundException();
		}
		Page<Product> pages = productService.findByCategory(thirdCategory,
				new PageRequest(page - 1, size));
		uiModel.addAttribute("pc", thirdCategory.getParent().getParent());
		uiModel.addAttribute("second", thirdCategory.getParent());
		uiModel.addAttribute("child", thirdCategory);
		uiModel.addAttribute("page", pages);
		return "product/products";
	}

	@RequestMapping(value = "/productCategory/{parent}/{second}", method = RequestMethod.GET)
	public String findBySecond(@PathVariable("second") String second,
			Model uiModel, @PathVariable("parent") String parent,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size) {
		ProductCategory secondCategory = pcService.findByName(second,
				ActivateEnum.ACTIVATE);
		if (secondCategory == null || secondCategory == null
				|| !secondCategory.getParent().getName().equals(parent))
			throw new CategoryNotFoundException();
		Page<Product> pages = productService.findBySecondCategory(
				secondCategory, new PageRequest(page - 1, size));

		uiModel.addAttribute("second", secondCategory);
		uiModel.addAttribute("pc", secondCategory.getParent());
		uiModel.addAttribute("page", pages);
		return "product/products";
	}

	private List<Product> setColl(List<Product> products) {
		SiteUser user = userContext.getCurrentUser();
		List<PreferProduct> prefer = preferProductService
				.findByUser(new CommonUser(user.getId()));
		if (prefer != null && prefer.size() != 0) {
			for (Product product : products) {
				Long productId = product.getId();
				for (int i = 0; i < prefer.size(); i++) {
					if (productId.equals(prefer.get(i).getProduct().getId())) {
						product.setCollection(true);
						break;
					}
				}
			}
		}
		return products;
	}

	@RequestMapping(value = "/productCategory/{name}", method = RequestMethod.GET)
	public String findByParent(@PathVariable("name") String name, Model uiModel) {
		ProductCategory pc = pcService.findByName(name, ActivateEnum.ACTIVATE);
		if (pc == null)
			throw new CategoryNotFoundException();
		List<ProductCategory> categories = pcService.findByParent(pc);
		uiModel.addAttribute("pc", pc);
		uiModel.addAttribute("categories", categories);
		return "/productcategory/productCategorys";
	}

}
