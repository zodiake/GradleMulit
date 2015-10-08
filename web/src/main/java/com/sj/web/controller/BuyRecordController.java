package com.sj.web.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.BuyRecord;
import com.sj.model.model.CartLine;
import com.sj.model.model.CommonUser;
import com.sj.model.model.SiteUser;
import com.sj.repository.service.BuyRecordService;
import com.sj.repository.service.CartLineService;
import com.sj.repository.service.CommonUserService;
import com.sj.web.annotation.SecurityUser;
import com.sj.web.exception.BuyRecordNotFoundException;
import com.sj.web.security.SiteUserContext;

@Controller
public class BuyRecordController extends BaseController<BuyRecord>{
	@Autowired
	private SiteUserContext userContext;
	@Autowired
	private SiteUserContext siteUserContext;
	@Autowired
	private BuyRecordService buyRecordService;
	@Autowired
	private CommonUserService commonUserService;
	@Autowired
	private CartLineService cartLineService;

	@RequestMapping(value = "/user/buyRecords", method = RequestMethod.GET)
	public String findAll(Model uiModel,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "20") int size) {
		SiteUser user = siteUserContext.getCurrentUser();
		Page<BuyRecord> pages = buyRecordService.findPage(
				new CommonUser(user.getId()), new PageRequest(page , size));
		uiModel.addAttribute("pages", pages);
		
		ViewPage viewpage = caculatePage(pages);
		viewpage.setHref("/user/buyRecords");
		viewpage.setCurrent(pages.getNumber());
		uiModel.addAttribute("viewpage", viewpage);
		
		return "user/common/buys";
	}

	@RequestMapping(value = "/user/buyRecords/{id}", method = RequestMethod.GET)
	public String findOne(@PathVariable("id") Long id, Model uiModel,@SecurityUser SiteUser user) {
		BuyRecord buyRecord = buyRecordService.findOne(id,
				new CommonUser(user.getId()));
		if (buyRecord == null)
			throw new BuyRecordNotFoundException();
		uiModel.addAttribute("buy", buyRecord);
		return "user/common/buy";
	}

	@RequestMapping(value = "/user/buyRecords/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteOne(@PathVariable("id") Long id) {
		buyRecordService.deleteOne(id);
		return "success";
	}

	@RequestMapping(value = "/user/buyRecords/{id}", method = RequestMethod.GET, params = "edit")
	public String update(@PathVariable("id") Long id, Model uiModel,
			@SecurityUser SiteUser user) {
		BuyRecord buyRecord = buyRecordService.findOne(id,
				new CommonUser(user.getId()));
		if (buyRecord == null)
			throw new BuyRecordNotFoundException();
		uiModel.addAttribute("buy", buyRecord);
		return "user/common/modifyBuy";
	}

	@RequestMapping(value = "/user/buyRecords/{id}", method = RequestMethod.PUT, params = "edit")
	public String updateProcess(
			@Valid @ModelAttribute("buy") BuyRecord buyRecord,
			BindingResult result, @PathVariable("id") Long id, Model uiModel,
			@SecurityUser SiteUser user) throws ParseException {
		if(result.hasErrors()){
			BuyRecord newBuy = buyRecordService.findOne(id, new CommonUser(user.getId()));
			buyRecord.setProducts(newBuy.getProducts());
			buyRecord.setPrice(newBuy.getPrice());
			buyRecord.setId(id);
			CommonUser common = commonUserService.findOne(user.getId());
			buyRecord.setUser(common);
			uiModel.addAttribute("buy", buyRecord);
			return "user/common/modifyBuy";
		}
		buyRecord = buyRecordService.update(new CommonUser(user.getId()),buyRecord);
		uiModel.addAttribute("buy", buyRecord);
		return "redirect:/user/buyRecords/" + id ;
	}

	@RequestMapping(value = "/user/buyRecords", method = RequestMethod.GET, params = "form")
	public String createBuyRecord(Model uiModel, @SecurityUser SiteUser user) {
		List<CartLine> lines = cartLineService.findByUserAndCheck(user.getId());
		float totalPrice = 0f;
		for (CartLine cartLine : lines) {
			totalPrice = totalPrice + cartLine.getPrice()*cartLine.getNumber();
		}
		CommonUser commonUser = commonUserService.findOne(user.getId());
		uiModel.addAttribute("lines", lines);
		BuyRecord buy = new BuyRecord();
		buy.setUser(commonUser);
		uiModel.addAttribute("buy", buy);
		uiModel.addAttribute("totalPrice", totalPrice);
		return "user/common/createBuy";
	}

	@RequestMapping(value = "/user/buyRecords", method = RequestMethod.POST, params = "form")
	public String createBuyRecordProcess(
			@Valid @ModelAttribute("buy") BuyRecord buyRecord,BindingResult result, Model uiModel,
			@SecurityUser SiteUser user,HttpSession session) throws ParseException {
		if(result.hasErrors()){
			CommonUser common = commonUserService.findOne(user.getId());
			buyRecord.setUser(common);
			uiModel.addAttribute("buy", buyRecord);
			List<CartLine> lines = cartLineService.findByUserAndCheck(user.getId());
			float totalPrice = 0f;
			for (CartLine cartLine : lines) {
				totalPrice = totalPrice + cartLine.getPrice()*cartLine.getNumber();
			}
			uiModel.addAttribute("totalPrice", totalPrice);
			uiModel.addAttribute("lines", lines);
			return "user/common/createBuy";
		}
		List<CartLine> lines = cartLineService.findByUserAndCheck(user.getId());
		buyRecord.setUser(new CommonUser(user.getId()));
		buyRecordService.save(buyRecord, lines);
		lines = cartLineService.findByUser(user.getId());
		session.setAttribute("cartLines", lines);
		return "redirect:/user/buyRecords/"+buyRecord.getId();
	}
}
