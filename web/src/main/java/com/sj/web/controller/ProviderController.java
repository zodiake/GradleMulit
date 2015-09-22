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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.model.model.Consumable;
import com.sj.model.model.Instrument;
import com.sj.model.model.Product;
import com.sj.model.model.ProductCategory;
import com.sj.model.model.Provider;
import com.sj.model.model.Reagents;
import com.sj.model.model.Service;
import com.sj.model.model.SiteUser;
import com.sj.model.type.ActivateEnum;
import com.sj.model.type.ProductStatusEnum;
import com.sj.repository.service.BrandService;
import com.sj.repository.service.CityService;
import com.sj.repository.service.ConsumableService;
import com.sj.repository.service.InstrumentService;
import com.sj.repository.service.ProductCategoryService;
import com.sj.repository.service.ProductService;
import com.sj.repository.service.ProviderIndustryInfoService;
import com.sj.repository.service.ProviderService;
import com.sj.repository.service.ProvinceService;
import com.sj.repository.service.ReagentsService;
import com.sj.repository.service.ServiceService;
import com.sj.repository.service.SiteUserService;
import com.sj.web.annotation.SecurityUser;
import com.sj.web.exception.EnumNotFoundException;
import com.sj.web.exception.ProductNotFoundException;
import com.sj.web.security.UserContext;

@Controller
public class ProviderController extends BaseController<Product> {
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
	@Autowired
	private ConsumableService consumableService;
	@Autowired
	private ReagentsService reagentsService;
	@Autowired
	private ServiceService serviceService;

	@RequestMapping(value = "/supplier/detail", method = RequestMethod.GET)
	public String findCurrentSupplier(Model uiModel) {
		SiteUser user = userContext.getCurrentUser();
		Provider provider = providerService.findById(user.getId());
		uiModel.addAttribute("user", provider);
		uiModel.addAttribute("provinces", provinceService.findAll());
		uiModel.addAttribute("industryInfos",
				providerIndustryInfoService.findAll());
		uiModel.addAttribute("citys", cityService.findByProvince(provider.getProvince()));
		return "user/provider/supplierDetail";
	}

	@RequestMapping(value = "/supplier/detail", method = RequestMethod.PUT)
	public String updateCurrentSupplier(Model uiModel,
			@Valid @ModelAttribute("user") Provider provider,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("user", provider);
			uiModel.addAttribute("provinces", provinceService.findAll());
			uiModel.addAttribute("industryInfos",providerIndustryInfoService.findAll());
			uiModel.addAttribute("citys",cityService.findByProvince(provider.getProvince()));
			return "user/provider/supplierDetail";
		}
		SiteUser user = userContext.getCurrentUser();
		provider.setId(user.getId());
		provider = providerService.updateProvider(provider);
		uiModel.addAttribute("user", provider);
		return "redirect:/supplier/detail";
	}
	@RequestMapping(value = "/provider/detail", method = RequestMethod.GET)
	public String findCurrentProvider(Model uiModel) {
		SiteUser user = userContext.getCurrentUser();
		Provider provider = providerService.findById(user.getId());
		uiModel.addAttribute("user", provider);
		uiModel.addAttribute("provinces", provinceService.findAll());
		uiModel.addAttribute("industryInfos",
				providerIndustryInfoService.findAll());
		uiModel.addAttribute("citys", cityService.findByProvince(provider.getProvince()));
		return "user/provider/providerDetail";
	}

	@RequestMapping(value = "/provider/detail", method = RequestMethod.PUT)
	public String updateCurrentProvider(Model uiModel,
			@Valid @ModelAttribute("user") Provider provider,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("user", provider);
			uiModel.addAttribute("provinces", provinceService.findAll());
			uiModel.addAttribute("industryInfos",providerIndustryInfoService.findAll());
			uiModel.addAttribute("citys",cityService.findByProvince(provider.getProvince()));
			return "user/provider/providerDetail";
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
		
		ViewPage viewpage = caculatePage(products);
		viewpage.setHref("/provider/products");
		uiModel.addAttribute("viewpage", viewpage);
		
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
						.valueOf(status));
		
		ViewPage viewpage = caculatePage(products);
		viewpage.setHref("/provider/products/"+status);
		uiModel.addAttribute("viewpage", viewpage);
		
		uiModel.addAttribute("lists", products);
		uiModel.addAttribute("status", ProductStatusEnum.valueOf(status));
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
			bindingResult
					.addError(new FieldError("product", "price", "价格不能为0"));
		}
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("brands", brandService.findAll());
			List<ProductCategory> pcs = productCategoryService.findAllFirstCategory(ActivateEnum.ACTIVATE);
			if (instrument.getFirstCategory() != null) {
				List<ProductCategory> secondCategory = productCategoryService.findByParentAndActivate(instrument.getFirstCategory(),
								ActivateEnum.ACTIVATE);
				uiModel.addAttribute("secondCategories", secondCategory);
			}
			if (instrument.getSecondCategory() != null) {
				List<ProductCategory> thirdCategory = productCategoryService.findByParentAndActivate(
								instrument.getSecondCategory(),ActivateEnum.ACTIVATE);
				uiModel.addAttribute("thirdCategories", thirdCategory);
			}
			uiModel.addAttribute("pcs", pcs);
			uiModel.addAttribute("product", instrument);
			return "user/provider/release";
		}
		instrument.setCreatedBy(new Provider(user.getId()));

		Instrument i =instrumentService.saveNoPublisher(instrument);
		return "redirect:/provider/products/"+i.getId()+"?detail";
	}

	@RequestMapping(value = "/provider/consumables", method = RequestMethod.POST, params = "form")
	public String createConsumable(
			@Valid @ModelAttribute("product") Consumable consumable,
			BindingResult bindingResult, Model uiModel,
			@SecurityUser SiteUser user) {
		float price = consumable.getPrice();
		if (price == 0.0f) {
			bindingResult
					.addError(new FieldError("product", "price", "价格不能为0"));
		}
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("brands", brandService.findAll());
			List<ProductCategory> pcs = productCategoryService
					.findAllFirstCategory(ActivateEnum.ACTIVATE);
			if (consumable.getFirstCategory() != null) {
				List<ProductCategory> secondCategory = productCategoryService
						.findByParentAndActivate(consumable.getFirstCategory(),
								ActivateEnum.ACTIVATE);
				uiModel.addAttribute("secondCategories", secondCategory);
			}
			if (consumable.getSecondCategory() != null) {
				List<ProductCategory> thirdCategory = productCategoryService
						.findByParentAndActivate(
								consumable.getSecondCategory(),
								ActivateEnum.ACTIVATE);
				uiModel.addAttribute("thirdCategories", thirdCategory);
			}
			uiModel.addAttribute("pcs", pcs);
			uiModel.addAttribute("product", consumable);
			return "user/provider/release";
		}
		consumable.setCreatedBy(new Provider(user.getId()));
		
		Consumable c = consumableService.saveNoPublisher(consumable);
		return "redirect:/provider/products/"+c.getId()+"?detail";
	}

	@RequestMapping(value = "/provider/reagents", method = RequestMethod.POST, params = "form")
	public String createReagents(
			@Valid @ModelAttribute("product") Reagents reagents,
			BindingResult bindingResult, Model uiModel,
			@SecurityUser SiteUser user) {
		float price = reagents.getPrice();
		if (price == 0.0f) {
			bindingResult
					.addError(new FieldError("product", "price", "价格不能为0"));
		}
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("brands", brandService.findAll());
			List<ProductCategory> pcs = productCategoryService
					.findAllFirstCategory(ActivateEnum.ACTIVATE);
			if (reagents.getFirstCategory() != null) {
				List<ProductCategory> secondCategory = productCategoryService
						.findByParentAndActivate(reagents.getFirstCategory(),
								ActivateEnum.ACTIVATE);
				uiModel.addAttribute("secondCategories", secondCategory);
			}
			if (reagents.getSecondCategory() != null) {
				List<ProductCategory> thirdCategory = productCategoryService
						.findByParentAndActivate(reagents.getSecondCategory(),
								ActivateEnum.ACTIVATE);
				uiModel.addAttribute("thirdCategories", thirdCategory);
			}
			uiModel.addAttribute("pcs", pcs);
			uiModel.addAttribute("product", reagents);
			return "user/provider/release";
		}
		reagents.setCreatedBy(new Provider(user.getId()));

		Reagents r = reagentsService.saveNoPublisher(reagents);
		return "redirect:/provider/products/"+r.getId()+"?detail";
	}

	@RequestMapping(value = "/provider/services", method = RequestMethod.POST, params = "form")
	public String createService(
			@Valid @ModelAttribute("product") Service service,
			BindingResult bindingResult, Model uiModel,
			@SecurityUser SiteUser user) {
		float price = service.getPrice();
		if (price == 0.0f) {
			bindingResult
					.addError(new FieldError("product", "price", "价格不能为0"));
		}
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("brands", brandService.findAll());
			List<ProductCategory> pcs = productCategoryService
					.findAllFirstCategory(ActivateEnum.ACTIVATE);
			if (service.getFirstCategory() != null) {
				List<ProductCategory> secondCategory = productCategoryService
						.findByParentAndActivate(service.getFirstCategory(),
								ActivateEnum.ACTIVATE);
				uiModel.addAttribute("secondCategories", secondCategory);
			}
			if (service.getSecondCategory() != null) {
				List<ProductCategory> thirdCategory = productCategoryService
						.findByParentAndActivate(service.getSecondCategory(),
								ActivateEnum.ACTIVATE);
				uiModel.addAttribute("thirdCategories", thirdCategory);
			}
			uiModel.addAttribute("pcs", pcs);
			uiModel.addAttribute("product", service);
			return "user/provider/release";
		}
		service.setCreatedBy(new Provider(user.getId()));

		Service s = serviceService.saveNoPublisher(service);
		return "redirect:/provider/products/"+s.getId()+"?detail";
	}

	/* 商品发布 end */

	/* 商品修改 */
	@RequestMapping(value = "/provider/products/{id}", method = RequestMethod.GET, params = "edit")
	public String edit(Model uiModel, @PathVariable("id") Long id) {
		SiteUser siteUser = userContext.getCurrentUser();
		Product product = productService.findOneByUser(
				new Provider(siteUser.getId()), id);
		uiModel.addAttribute("product", product);
		uiModel.addAttribute("brands", brandService.findAll());
		List<ProductCategory> secondCategories = productCategoryService
				.findByParent(product.getFirstCategory());
		List<ProductCategory> thirdCategories = productCategoryService
				.findByParent(product.getSecondCategory());
		uiModel.addAttribute("seconds", secondCategories);
		uiModel.addAttribute("thirds", thirdCategories);
		return "user/provider/modifyProduct";
	}

	@RequestMapping(value = "/provider/instruments/{id}", method = RequestMethod.PUT, params = "edit")
	public String editInstrument(@PathVariable("id") Long id,
			@Valid @ModelAttribute("product") Instrument instrument,
			BindingResult result, Model uiModel) {
		float price = instrument.getPrice();
		if (price == 0.0f) {
			result.addError(new FieldError("product", "price", "价格不能为0"));
		}
		if (result.hasErrors()) {
			uiModel.addAttribute("product", instrument);
			uiModel.addAttribute("brands", brandService.findAll());
			instrument.setFirstCategory(productCategoryService.findOne(1l));
			List<ProductCategory> secondCategories = productCategoryService
					.findByParent(instrument.getFirstCategory());
			List<ProductCategory> thirdCategories = productCategoryService
					.findByParent(instrument.getSecondCategory());
			uiModel.addAttribute("seconds", secondCategories);
			uiModel.addAttribute("thirds", thirdCategories);
			return "user/provider/modifyProduct";
		}
		instrument.setId(id);
		instrument = instrumentService.updateNoPublisher(instrument);
		uiModel.addAttribute("product", instrument);
		return "redirect:/provider/products/"+id+"?detail";
	}

	@RequestMapping(value = "/provider/consumables/{id}", method = RequestMethod.PUT, params = "edit")
	public String editConsumable(@PathVariable("id") Long id,
			@Valid @ModelAttribute("product") Consumable consumable,
			BindingResult result, Model uiModel) {
		float price = consumable.getPrice();
		if (price == 0.0f) {
			result.addError(new FieldError("product", "price", "价格不能为0"));
		}
		if (result.hasErrors()) {
			uiModel.addAttribute("product", consumable);
			consumable.setFirstCategory(productCategoryService.findOne(1l));
			List<ProductCategory> secondCategories = productCategoryService
					.findByParent(consumable.getFirstCategory());
			List<ProductCategory> thirdCategories = productCategoryService
					.findByParent(consumable.getSecondCategory());
			uiModel.addAttribute("seconds", secondCategories);
			uiModel.addAttribute("thirds", thirdCategories);
			return "user/provider/modifyProduct";
		}
		consumable.setId(id);
		consumable = consumableService.updateNoPublisher(consumable);
		uiModel.addAttribute("product", consumable);
		return "redirect:/provider/products/"+id+"?detail";
	}

	@RequestMapping(value = "/provider/services/{id}", method = RequestMethod.PUT, params = "edit")
	public String editService(@PathVariable("id") Long id,
			@Valid @ModelAttribute("product") Service service,
			BindingResult result, Model uiModel) {
		float price = service.getPrice();
		if (price == 0.0f) {
			result.addError(new FieldError("product", "price", "价格不能为0"));
		}
		if (result.hasErrors()) {
			uiModel.addAttribute("product", service);
			service.setFirstCategory(productCategoryService.findOne(1l));
			List<ProductCategory> secondCategories = productCategoryService
					.findByParent(service.getFirstCategory());
			List<ProductCategory> thirdCategories = productCategoryService
					.findByParent(service.getSecondCategory());
			uiModel.addAttribute("seconds", secondCategories);
			uiModel.addAttribute("thirds", thirdCategories);
			return "user/provider/modifyProduct";
		}
		service.setId(id);
		service = serviceService.updateNoPublisher(service);
		uiModel.addAttribute("product", service);
		return "redirect:/provider/products/"+id+"?detail";
	}
	
	@RequestMapping(value = "/provider/reagents/{id}", method = RequestMethod.PUT, params = "edit")
	public String editReagents(@PathVariable("id") Long id,
			@Valid @ModelAttribute("product") Reagents reagents,
			BindingResult result, Model uiModel) {
		float price = reagents.getPrice();
		if (price == 0.0f) {
			result.addError(new FieldError("product", "price", "价格不能为0"));
		}
		if (result.hasErrors()) {
			uiModel.addAttribute("product", reagents);
			reagents.setFirstCategory(productCategoryService.findOne(1l));
			List<ProductCategory> secondCategories = productCategoryService
					.findByParent(reagents.getFirstCategory());
			List<ProductCategory> thirdCategories = productCategoryService
					.findByParent(reagents.getSecondCategory());
			uiModel.addAttribute("seconds", secondCategories);
			uiModel.addAttribute("thirds", thirdCategories);
			return "user/provider/modifyProduct";
		}

		Product oldProduct = productService.findOne(id);
		if (oldProduct == null) {
			throw new ProductNotFoundException();
		}
		reagents.setId(id);
		reagents = reagentsService.updateNoPublisher(reagents);
		uiModel.addAttribute("product", reagents);
		return "redirect:/provider/products/"+id+"?detail";
	}

	/* 商品修改 end */
	@RequestMapping(value = "/provider/products/{id}/{status}", method = RequestMethod.PUT)
	public String offProduct(@PathVariable("id") Long id,
			@PathVariable("status") String status, @SecurityUser SiteUser user,
			Model uiModel) {
		Product product = productService.findOneByUser(
				new Provider(user.getId()), id);
		if (product == null)
			throw new ProductNotFoundException();
		ProductStatusEnum productStatusEnum = ProductStatusEnum.valueOf(status);
		if (productStatusEnum == null)
			throw new EnumNotFoundException();
		product = productService.updateStatus(product,
				ProductStatusEnum.valueOf(status));
		uiModel.addAttribute("product", product);
		return "user/provider/maintaintd";
	}

	@RequestMapping(value = "/provider/count", method = RequestMethod.GET)
	public String findCount(Model uiModel, @SecurityUser SiteUser user,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size) {
		Page<Product> products = productService.findCount(new Provider(user.getId()), new PageRequest(page - 1, size,
				Direction.DESC, "createdTime"));
		ViewPage viewpage = caculatePage(products);
		viewpage.setHref("/provider/count");
		uiModel.addAttribute("viewpage", viewpage);
		
		uiModel.addAttribute("products", products);
		return "user/provider/count";
	}
}
