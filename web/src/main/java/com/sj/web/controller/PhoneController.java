package com.sj.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
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

import com.sj.model.model.SiteUser;
import com.sj.repository.service.SiteUserService;

@Controller
public class PhoneController {
	@Autowired
	private SiteUserService userService;

	private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

	@RequestMapping(value = "/users/phone",method = RequestMethod.POST)
	@ResponseBody
	public String verificationUserPhone(@RequestParam("phone") String phone,
			HttpSession session) throws HttpException, IOException, DocumentException {
		SiteUser user = userService.findByPhone(phone);
		if (user != null)
			return "exists";
		int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
		String code = sendRequest(phone, mobile_code);
		if("2".equals(code))
			session.setAttribute("uCode", String.valueOf(mobile_code));
		else
			return "error";
		return "success";
	}
	@RequestMapping(value = "/providers/phone" ,method = RequestMethod.POST)
	@ResponseBody
	public String verificationProviderPhone(@RequestParam("phone") String phone,
			HttpSession session) throws HttpException, IOException, DocumentException {
		SiteUser user = userService.findByPhone(phone);
		if (user != null)
			return "exists";
		int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
		String code = sendRequest(phone, mobile_code);
		if("2".equals(code))
			session.setAttribute("pCode", String.valueOf(mobile_code));
		else
			return "error";
		return "success";
	}
	@RequestMapping(value = "/forgetPws/phone" ,method = RequestMethod.POST)
	@ResponseBody
	public String verificationForgetPhone(@RequestParam("phone") String phone,
			HttpSession session) throws HttpException, IOException, DocumentException {
		SiteUser user = userService.findByPhone(phone);
		if (user == null)
			return "noexists";
		int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
		String code = sendRequest(phone, mobile_code);
		if("2".equals(code))
			session.setAttribute("fCode", String.valueOf(mobile_code));
		else
			return "error";
		return "success";
	}

	public String sendRequest(String phone, int mobile_code)
			throws HttpException, IOException, DocumentException {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(Url);

		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType",
				"application/x-www-form-urlencoded;charset=UTF-8");

		String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");

		NameValuePair[] data = {
				new NameValuePair("account", "cf_shenjie"),
				new NameValuePair("password", "123456"),
				new NameValuePair("mobile", phone),
				new NameValuePair("content", content), };

		method.setRequestBody(data);

		client.executeMethod(method);

		String SubmitResult = method.getResponseBodyAsString();

		Document doc = DocumentHelper.parseText(SubmitResult);
		Element root = doc.getRootElement();

		String code = root.elementText("code");
		return code;

	}
}
