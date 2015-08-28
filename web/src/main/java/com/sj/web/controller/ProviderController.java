package com.sj.web.controller;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.model.model.Instrument;
import com.sj.model.model.Product;
import com.sj.model.model.ProductCategory;
import com.sj.model.model.Provider;
import com.sj.model.model.SiteUser;
import com.sj.model.type.ActivateEnum;
import com.sj.model.type.ProductStatusEnum;
import com.sj.repository.service.BrandService;
import com.sj.repository.service.CityService;
import com.sj.repository.service.InstrumentService;
import com.sj.repository.service.ProductCategoryService;
import com.sj.repository.service.ProductService;
import com.sj.repository.service.ProviderIndustryInfoService;
import com.sj.repository.service.ProviderService;
import com.sj.repository.service.ProvinceService;
import com.sj.repository.service.SiteUserService;
import com.sj.web.annotation.SecurityUser;
import com.sj.web.exception.EnumNotFoundException;
import com.sj.web.exception.ProductNotFoundException;
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
	@Autowired
	private ProviderIndustryInfoService providerIndustryInfoService;
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private CityService cityService;
	@Autowired
	private InstrumentService instrumentService;

	@RequestMapping(value = "/provider/detail", method = RequestMethod.GET)
	public String findCurrentProvider(Model uiModel) {
		SiteUser user = userContext.getCurrentUser();
		Provider provider = providerService.findById(user.getId());
		uiModel.addAttribute("user", provider);
		uiModel.addAttribute("provinces", provinceService.findAll());
		uiModel.addAttribute("industryInfos",
				providerIndustryInfoService.findAll());
		uiModel.addAttribute("citys",
				cityService.findByProvince(provider.getProvince()));
		return "user/provider/detail";
	}

	@RequestMapping(value = "/provider/detail", method = RequestMethod.PUT)
	public String updateCurrentProvider(Model uiModel,
			@Valid @ModelAttribute("user") Provider provider,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("user", provider);
			uiModel.addAttribute("provinces", provinceService.findAll());
			uiModel.addAttribute("industryInfos",
					providerIndustryInfoService.findAll());
			uiModel.addAttribute("citys",
					cityService.findByProvince(provider.getProvince()));
			return "user/provider/detail";
		}
		SiteUser user = userContext.getCurrentUser();
		provider.setId(user.getId());
		provider = providerService.updateProvider(provider);
		uiModel.addAttribute("user", provider);
		return "redirect:/provider/detail";
	}

	@RequestMapping(value = "/provider/products", method = RequestMethod.GET)
	public String findAllProductByProvider(Model uiModel,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size,
			@SecurityUser SiteUser user) {
		Page<Product> products = productService.findByUsers(
				new Provider(user.getId()), new PageRequest(page - 1, size,
						Direction.DESC, "createdTime"));
		uiModel.addAttribute("lists", products);
		return "user/provider/maintain";
	}

	@RequestMapping(value = "/provider/products/{status}", method = RequestMethod.GET)
	public String findAllProductByProviderAndStatus(Model uiModel,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size,
			@SecurityUser SiteUser user, @PathVariable("status") String status) {
		Page<Product> products = productService.findByUsers(
				new Provider(user.getId()), new PageRequest(page - 1, size,
						Direction.DESC, "createdTime"), ProductStatusEnum
						.stringToEnum(status));
		uiModel.addAttribute("lists", products);
		uiModel.addAttribute("status", ProductStatusEnum.stringToEnum(status));
		return "user/provider/maintain";
	}

	/* 商品发布 */
	@RequestMapping(value = "/provider/products", params = "form", method = RequestMethod.GET)
	public String create(Model uiModel) {
		uiModel.addAttribute("product", new Product());
		uiModel.addAttribute("brands", brandService.findAll());
		List<ProductCategory> pcs = productCategoryService
				.findAllFirstCategory(ActivateEnum.ACTIVATE);
		uiModel.addAttribute("pcs", pcs);
		return "user/provider/release";
	}

	@RequestMapping(value = "/provider/instruments", method = RequestMethod.POST, params = "form")
	public String createInstrument(
			@Valid @ModelAttribute("product") Instrument instrument,
			BindingResult bindingResult, Model uiModel,
			@SecurityUser SiteUser user) {
		float price = instrument.getPrice();
		if (price == 0.0f) {
			bindingResult.addError(new FieldError("product", "price", "价格不能为0"));
		}
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("brands", brandService.findAll());
			List<ProductCategory> pcs = productCategoryService.findAllFirstCategory(ActivateEnum.ACTIVATE);
			uiModel.addAttribute("pcs", pcs);
			uiModel.addAttribute("product", instrument);
			return "user/provider/release";
		}
		instrument.setCreatedBy(new Provider(user.getId()));
		instrument.setCreatedTime(Calendar.getInstance());
		instrument.setStatus(ProductStatusEnum.EXAMINE);
		instrumentService.save(instrument);
		return "redirect:/provider/products";
	}

	/* 商品发布 end */

	@RequestMapping(value = "/provider/products/{id}", method = RequestMethod.GET, params = "edit")
	public String edit(Model uiModel, @PathVariable("id") Long id) {
		SiteUser siteUser = userContext.getCurrentUser();
		Product product = productService.findOneByUser(
				new Provider(siteUser.getId()), id);
		uiModel.addAttribute("product", product);
		uiModel.addAttribute("brands", brandService.findAll());
		List<ProductCategory> pcs = productCategoryService
				.findAllFirstCategory(ActivateEnum.ACTIVATE);
		List<ProductCategory> secondCategories = productCategoryService
				.findByParent(product.getFirstCategory());
		List<ProductCategory> thirdCategories = productCategoryService
				.findByParent(product.getSecondCategory());
		uiModel.addAttribute("pcs", pcs);
		uiModel.addAttribute("seconds", secondCategories);
		uiModel.addAttribute("thirds", thirdCategories);
		return "user/provider/modifyProduct";
	}

	@RequestMapping(value = "/provider/products/{id}", method = RequestMethod.PUT, params = "edit")
	public String editProcess(@PathVariable("id") Long id,
			@Valid @ModelAttribute("product") Product product,
			BindingResult result, Model uiModel) {
		float price = product.getPrice();
		if (price == 0.0f) {
			result.addError(new FieldError("product", "price", "价格不能为0"));
		}
		if (result.hasErrors()) {
			uiModel.addAttribute("product", product);
			return "user/provider/modifyProduct";
		}

		Product oldProduct = productService.findOne(id);
		if (oldProduct == null) {
			throw new ProductNotFoundException();
		}
		product = productService.updateProduct(product, oldProduct);
		uiModel.addAttribute("product", product);
		return null;
	}

	@RequestMapping(value = "/provider/products/{id}/{status}", method = RequestMethod.PUT)
	public String offProduct(@PathVariable("id") Long id,
			@PathVariable("status") String status, @SecurityUser SiteUser user,
			Model uiModel) {
		Product product = productService.findOneByUser(
				new Provider(user.getId()), id);
		if (product == null)
			throw new ProductNotFoundException();
		ProductStatusEnum productStatusEnum = ProductStatusEnum
				.stringToEnum(status);
		if (productStatusEnum == null)
			throw new EnumNotFoundException();
		product = productService.updateStatus(product,
				ProductStatusEnum.stringToEnum(status));
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
