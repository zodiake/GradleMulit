package com.sj.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.CommonUser;
import com.sj.model.model.PreferProduct;
import com.sj.model.model.Product;
import com.sj.model.model.SiteUser;
import com.sj.repository.service.BuyProductService;
import com.sj.repository.service.BuyRecordService;
import com.sj.repository.service.CartLineService;
import com.sj.repository.service.CityService;
import com.sj.repository.service.CommonUserService;
import com.sj.repository.service.PreferProductService;
import com.sj.repository.service.ProductService;
import com.sj.repository.service.ProvinceService;
import com.sj.repository.service.UserIndustryInfoService;
import com.sj.web.annotation.SecurityUser;
import com.sj.web.exception.ProductNotFoundException;
import com.sj.web.security.SiteUserContext;

@Controller
public class CollectionController extends BaseController<PreferProduct> {
	@Autowired
	private SiteUserContext userContext;
	@Autowired
	private CommonUserService commonUserService;
	@Autowired
	private PreferProductService preferService;
	@Autowired
	private ProductService productServise;
	@Autowired
	private UserIndustryInfoService userIndustryInfoService;
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private CityService cityService;
	@Autowired
	private BuyRecordService buyRecordService;
	@Autowired
	private BuyProductService buyProductService;
	@Autowired
	private SiteUserContext siteUserContext;
	@Autowired
	private CartLineService cartLineService;

	@RequestMapping(value = "/user/collection", method = RequestMethod.GET)
	public String getCollectionByUser(Model uiModel,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "12") int size) {
		SiteUser user = userContext.getCurrentUser();
		Page<PreferProduct> pages = preferService.findByUser(new CommonUser(
				user.getId()), new PageRequest(page, size));

		ViewPage viewPage = caculatePage(pages);
		viewPage.setHref("/user/collection");
		viewPage.setCurrent(pages.getNumber());
		
		uiModel.addAttribute("viewpage", viewPage);
		uiModel.addAttribute("page", pages);

		return "user/common/collection";
	}

	@RequestMapping(value = "/user/collection", method = RequestMethod.POST)
	public String addCollection(@SecurityUser SiteUser user,
			@RequestParam("id") Long productId) {
		Product p = productServise.findOne(productId);
		if (p == null)
			throw new ProductNotFoundException();
		PreferProduct prefer = new PreferProduct(new CommonUser(user.getId()),
				p);
		preferService.save(prefer);
		return "success";
	}

	@RequestMapping(value = "/user/collection/{productId}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteCollection(Model uiModel,
			@PathVariable(value = "productId") Long productId) {
		SiteUser user = userContext.getCurrentUser();
		preferService.deleteByUserAndProduct(new CommonUser(user.getId()),
				new Product(productId));
		return "success";
	}

}
