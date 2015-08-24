package com.sj.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.model.model.Product;
import com.sj.model.model.ProductCategory;
import com.sj.model.model.Provider;
import com.sj.model.model.SiteUser;
import com.sj.model.type.ActivateEnum;
import com.sj.model.type.OriginalEnum;
import com.sj.repository.service.BrandService;
import com.sj.repository.service.ProductCategoryService;
import com.sj.repository.service.ProductService;
import com.sj.repository.service.ProviderService;
import com.sj.repository.service.SiteUserService;
import com.sj.web.annotation.SecurityUser;
import com.sj.web.exception.ProductNotFoundException;
import com.sj.web.exception.UserNotFoundException;
import com.sj.web.security.UserContext;

@Controller
public class ProviderController extends BaseController<Provider> {
	@Autowired
	private SiteUserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProviderService providerService;
	@Autowired
	private UserContext userContext;
	@Autowired
	private BrandService brandService;
	@Autowired
	private ProductCategoryService productCategoryService;

	private final String USERPRODUCTSEDIT = "user/products/edit";
	private final String USERPRODUCTSEDITOK = "";
	private final String PROVIDERCREATEOK = "";

	@RequestMapping(value = "/providers", method = RequestMethod.GET)
	public String findAllProvider(Model uiModel) {
		Provider provider = providerService.findOne(6l);
		System.out.println(provider.getBusinessLicenseUrl());
		uiModel.addAttribute("provider", provider);
		return PROVIDERCREATEOK;
	}

	@RequestMapping(value = "/provider/detail", method = RequestMethod.GET)
	public String findCurrentProvider(Model uiModel) {
		SiteUser user = userContext.getCurrentUser();
		Provider provider = providerService.findOne(user.getId());
		if (provider == null) {
			throw new UserNotFoundException();
		}
		uiModel.addAttribute("user", provider);
		return "user/provider/detail";
	}

	@RequestMapping(value = "/provider/detail", method = RequestMethod.PUT)
	public String updateCurrentProvider(Model uiModel,
			@ModelAttribute("user") Provider provider,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("user", provider);
			return "user/provider/detail";
		}
		SiteUser user = userContext.getCurrentUser();
		provider.setId(user.getId());
		provider = providerService.updateProvider(provider);
		uiModel.addAttribute("user", provider);
		return "user/provider/detail";
	}

	@RequestMapping(value = "/provider/products/{status}", method = RequestMethod.GET)
	public String productLists(Model uiModel,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size,
			@SecurityUser SiteUser user, @PathVariable("status") String status) {
		System.out.println("user" + user.getId());
		Page<Product> products = null;
		if (status.equals(OriginalEnum.ALL.toString().toUpperCase())) {

			System.out.println("1");
			products = productService.findByUsers(new Provider(user.getId()),
					new PageRequest(page - 1, size, Direction.DESC,
							"createdTime"));
		} else {
			System.out.println("2");
			products = productService.findByUsers(new Provider(user.getId()),
					new PageRequest(page - 1, size, Direction.DESC,
							"createdTime"), OriginalEnum.fromString(status));
		}
		uiModel.addAttribute("lists", products);
		uiModel.addAttribute("status", OriginalEnum.fromString(status));
		return "user/provider/maintain";
	}

	/* 商品发布 */
	@RequestMapping(value = "/provider/products", params = "form", method = RequestMethod.GET)
	public String create(Model uiModel) {
		Product p = new Product();
		p.setName("123456");
		uiModel.addAttribute("product", new Product());
		uiModel.addAttribute("brand", brandService.findAll());
		List<ProductCategory> pcs = productCategoryService
				.findAllFirstCategory(ActivateEnum.ACTIVATE);
		uiModel.addAttribute("pcs", pcs);
		return "user/provider/release";
	}

	@RequestMapping(value = "/provider/products", method = RequestMethod.POST, params = "form")
	public String createProcess(
			@Valid @ModelAttribute("product") Product product,
			BindingResult bindingResult, Model uiModel,
			@SecurityUser SiteUser user) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("product", product);
			System.out.println("hasErrors");
			return "user/provider/release";
		}
		product.setCreatedBy(new Provider(user.getId()));
		// product = productService.addOneProduct(product);
		uiModel.addAttribute("product", product);
		return "user/provider/release";
	}

	/* 商品发布 end */

	@RequestMapping(value = "/provider/products/{id}", method = RequestMethod.GET, params = "edit")
	public String edit(Model uiModel, @PathVariable("id") Long id) {
		Provider user = (Provider) userContext.getCurrentUser();
		Product product = productService.findOneByUser(user, id);
		uiModel.addAttribute("product", product);
		return USERPRODUCTSEDIT;
	}

	@RequestMapping(value = "/provider/products/{id}", method = RequestMethod.PUT, params = "edit")
	public String editProcess(@PathVariable("id") Long id,
			@Valid @ModelAttribute("product") Product product,
			BindingResult request, Model uiModel) {
		Product oldProduct = productService.findOne(id);
		if (oldProduct == null) {
			throw new ProductNotFoundException();
		}
		product = productService.updateProduct(product, oldProduct);
		uiModel.addAttribute("product", product);
		return USERPRODUCTSEDITOK;
	}

	// 商品下架
	@RequestMapping(value = "/provider/products/{id}/{status}", method = RequestMethod.PUT)
	public String offProduct(@PathVariable("id") Long id,
			@PathVariable("status") String status, @SecurityUser SiteUser user,
			Model uiModel) {
		Product product = productService.findOneByUser(
				new Provider(user.getId()), id);
		if (product == null)
			throw new ProductNotFoundException();
		product.setOriginal(OriginalEnum.valueOf(status));
		product = productService.saveOne(product);
		uiModel.addAttribute("product", product);
		return "user/provider/maintaintd";
	}

	@RequestMapping(value = "/provider/count", method = RequestMethod.GET)
	public String findCount(Model uiModel, @SecurityUser SiteUser user,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size) {
		Page<Product> products = productService.findByUsers(
				new Provider(user.getId()), new PageRequest(page - 1, size,
						Direction.DESC, "createdTime"));
		uiModel.addAttribute("products", products);
		return "user/provider/count";
	}
}
