package com.sj.web.controller;

import java.util.Arrays;
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

import com.sj.model.model.PreferProduct;
import com.sj.model.model.Product;
import com.sj.model.model.ProductCategory;
import com.sj.model.model.SiteUser;
import com.sj.model.type.ActivateEnum;
import com.sj.model.type.ProductStatusEnum;
import com.sj.repository.service.BrandService;
import com.sj.repository.service.PreferProductService;
import com.sj.repository.service.ProductCategoryService;
import com.sj.repository.service.ProductService;
import com.sj.web.exception.CategoryNotFoundException;
import com.sj.web.exception.ProductNotFoundException;
import com.sj.web.security.SiteUserContext;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private ProductCategoryService pcService;
	@Autowired
	private PreferProductService preferProductService;
	@Autowired
	private SiteUserContext userContext;

	private final String DETAIL = "product/detail";

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public String view(Model uiModel, @PathVariable(value = "id") Long id) {
		Product product = productService.findOne(id);
		if (product == null)
			throw new ProductNotFoundException();
		productService.addViewCount(id);
		uiModel.addAttribute("product", product);
		return DETAIL;
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.POST)
	public void addCount(@PathVariable(value = "id") Long id) {
		productService.addViewCount(id);
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET, params = "form")
	public String create(Model uiModel) {
		uiModel.addAttribute("product", new Product());
		return null;
	}

	@RequestMapping(value = "/products/category/{parent}/{second}/{child}", method = RequestMethod.GET)
	public String findProductsByCategory(@PathVariable("parent") String parent,@PathVariable("second")String second,
			@PathVariable("child") String child, Model uiModel,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size) {
		ProductCategory pc = ProductCategory.getFirst(parent);
		uiModel.addAttribute("pc", pc);
		if(pc==null)
			throw new CategoryNotFoundException();
		ProductCategory categorySecond = pcService.findByNameAndParentAndState(second, pc, ActivateEnum.ACTIVATE);
		if(categorySecond==null)
			throw new CategoryNotFoundException();
		ProductCategory category = pcService.findByNameAndParentAndState(child, categorySecond, ActivateEnum.ACTIVATE);
		if(category==null)
			throw new CategoryNotFoundException();
		uiModel.addAttribute("category", category);
		Page<Product> pages = productService.findByCategory(category,
				new PageRequest(page - 1, size));
		if(pages.getContent().size()==0)
			throw new ProductNotFoundException();
		uiModel.addAttribute("pages", pages);
		System.out.println(pages.getContent().size());
		List<Product> products = pages.getContent();
		try {
			SiteUser user = userContext.getCurrentUser();
			List<PreferProduct> prefers = preferProductService.findByUser(user);
			for (Product product : products) {
				Long id = product.getId();
				for (int i = 0; i < prefers.size(); i++) {
					if(id.equals(prefers.get(i).getId())){
						product.setCollection(false);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		uiModel.addAttribute("products", products);
		return "product/products";
	}

}