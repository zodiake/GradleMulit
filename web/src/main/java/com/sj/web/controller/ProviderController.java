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
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.Brand;
import com.sj.model.model.Product;
import com.sj.model.model.Provider;
import com.sj.model.model.SiteUser;
import com.sj.model.model.Subject;
import com.sj.model.model.SubjectCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.model.type.OriginalEnum;
import com.sj.repository.service.ProductService;
import com.sj.repository.service.ProviderService;
import com.sj.repository.service.SiteUserService;
import com.sj.repository.service.SubjectCategoryService;
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
	private SubjectCategoryService categoryService;

	private final String USERPRODUCTS = "user/products";
	private final String USERPRODUCTSCREATE = "user/products/create";
	private final String USERPRODUCTSCTEATEOK = "";
	private final String USERPRODUCTSEDIT = "user/products/edit";
	private final String USERPRODUCTSEDITOK = "";
	private final String PROVIDERCREATE = "user/provider/create";
	private final String PROVIDERCREATEOK = ""; 
	private final String PROVIDER = "user/providers";

	@RequestMapping(value = "/providers", method = RequestMethod.GET, params = "form")
	public String createProvider(Model uiModel) {
		uiModel.addAttribute("provider", new Provider());
		return PROVIDERCREATE;
	}

	@RequestMapping(value = "/providers", method = RequestMethod.POST, params = "form")
	public String createProviderProcess(
			@Valid @ModelAttribute("provider") Provider provider,
			BindingResult bindingResult, Model uiModel) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("provider", provider);
			return PROVIDERCREATE;
		}
		provider = providerService.regirectedProvider(provider);
		uiModel.addAttribute("provider", provider);
		return PROVIDERCREATE;
	}
	
	@RequestMapping(value="/providers",method = RequestMethod.GET)
	public String findAllProvider(Model uiModel){
		Provider provider = providerService.findOne(3l);
		uiModel.addAttribute("provider", provider);
		return PROVIDERCREATEOK;
	}
	
	@RequestMapping(value = "/currentProviders",method = RequestMethod.GET)
	public String findCurrentProvider(Model uiModel){
		SiteUser user =  userContext.getCurrentUser();
		Provider provider = providerService.findOne(user.getId());
		if(provider == null){
			throw new UserNotFoundException();
		}
		uiModel.addAttribute("provider", provider);
		return PROVIDER;
	}

	@RequestMapping(value = "/provider/products", method = RequestMethod.GET)
	public String productLists(Model uiModel,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size) {
		SiteUser user = userContext.getCurrentUser();
		Page<Product> products = productService.findByUsers(
				new Provider(user.getId()), new PageRequest(page - 1, size,
						Direction.DESC, "createdTime"));
		uiModel.addAttribute("lists", products);
		return USERPRODUCTS;
	}

	@RequestMapping(value = "/proivder/products", params = "form", method = RequestMethod.GET)
	public String create(Model uiModel) {
		uiModel.addAttribute("product", new Product());
		// 以下值均要进行初始化，需要修改
		uiModel.addAttribute("subject", new Subject());
		List<SubjectCategory> categories = categoryService
				.findByActivate(ActivateEnum.ACTIVATE);
		uiModel.addAttribute("firstCategory", categories);
		uiModel.addAttribute("brand", new Brand());
		return USERPRODUCTSCREATE;
	}

	@RequestMapping(value = "/provider/products", method = RequestMethod.POST, params = "form")
	public String createProcess(
			@Valid @ModelAttribute("product") Product product, Model uiModel) {
		Provider user = (Provider) userContext.getCurrentUser();
		product.setCreatedBy(user);
		product.setOriginal(OriginalEnum.IN);
		// 添加其他信息
		Product newProduct = productService.addOneProduct(product);
		uiModel.addAttribute("product", newProduct);
		return USERPRODUCTSCTEATEOK;
	}

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
	@RequestMapping(value = "/provider/products/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable("id") Long id) {
		Product product = productService.findOne(id);
		if (product == null)
			throw new ProductNotFoundException();
		product.setOriginal(OriginalEnum.OUT);
		productService.offProduct(product);
		return "";
	}
}
