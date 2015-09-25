package com.sj.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.sj.model.model.SiteUser;
import com.sj.repository.service.SiteUserService;

@Controller
public class PhoneController {
	@Autowired
	private SiteUserService userService;


	@RequestMapping(value = "/users/captcha", method = RequestMethod.POST)
	@ResponseBody
	public String verificationUserPhone(@RequestParam("phone") String phone,
			HttpSession session) throws DocumentException {
		int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
		String code = sendRequest(phone, mobile_code);
		System.out.println(code);
		if ("2".equals(code))
			session.setAttribute("uCode", String.valueOf(mobile_code));
		else if("4085".equals(code))
			return "limit";
		else
			return "error";
		return "success";
	}

	@RequestMapping(value = "/providers/captcha", method = RequestMethod.POST)
	@ResponseBody
	public String verificationProviderPhone(
			@RequestParam("phone") String phone, HttpSession session)
			throws DocumentException {
		SiteUser user = userService.findByPhone(phone);
		if (user != null)
			return "exists";
		int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
		String code = sendRequest(phone, mobile_code);
		if ("2".equals(code))
			session.setAttribute("pCode", String.valueOf(mobile_code));
		else
			return "error";
		return "success";
	}

	@RequestMapping(value = "/forgetPws/captcha", method = RequestMethod.POST)
	@ResponseBody
	public String verificationForgetPhone(@RequestParam("phone") String phone,
			HttpSession session) throws DocumentException {
		SiteUser user = userService.findByPhone(phone);
		if (user == null)
			return "noexists";
		int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
		String code = sendRequest(phone, mobile_code);
		if ("2".equals(code))
			session.setAttribute("fCode", String.valueOf(mobile_code));
		else
			return "error";
		return "success";
	}

	public String sendRequest(String phone, int mobile_code) throws DocumentException {
		String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");
		RestTemplate rest = new RestTemplate();
		Map<String, String> data = new HashMap<String, String>();
		data.put("account", "cf_shenjie");
		data.put("password", "123456");
		data.put("mobile", phone);
		data.put("content", content);
		String postUrl = "http://106.ihuyi.cn/webservice/sms.php?method=Submit&account={account}&password={password}&mobile={mobile}&content={content}";
		String str = rest.postForObject(postUrl, null, String.class,data);
		System.out.println(str);
		
		Document doc = DocumentHelper.parseText(str);
		Element root = doc.getRootElement();
		String code = root.elementText("code");
		return code;
	}

}
