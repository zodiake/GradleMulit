package com.sj.web.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.code.kaptcha.Constants;
import com.sj.repository.service.CaptchaService;
import com.sj.repository.util.TextCaptcha;

@Controller
public class CaptchaController {
	@Autowired
	private CaptchaService captchaService;

	@RequestMapping(value = "/captcha/img", method = RequestMethod.GET)
	public void captchaImg(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		sendImg(session, response);
	}
	@RequestMapping(value = "/captcha/text", method = RequestMethod.GET)
	public void captchaText(HttpSession session){
		String captcha=captchaService.createStringCaptcha();
		session.setAttribute("captcha", captcha);
		sendText();
	}

	private void sendText(){
		//todo
	}

	private void sendImg(HttpSession session, HttpServletResponse response) {

		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");

		// return a jpeg
		response.setContentType("image/jpeg");

		// create the text for the image
		TextCaptcha textCaptcha = captchaService.createImgCaptcha();

		String capText = textCaptcha.getText();

		// store the text in the session
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

		// create the image with the text
		BufferedImage bi = textCaptcha.getImg();

		ServletOutputStream out;
		try {
			out = response.getOutputStream();
			ImageIO.write(bi, "jpg", out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
