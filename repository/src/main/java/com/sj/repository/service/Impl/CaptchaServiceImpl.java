package com.sj.repository.service.Impl;


import java.awt.image.BufferedImage;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.kaptcha.Producer;
import com.sj.repository.service.CaptchaService;
import com.sj.repository.util.TextCaptcha;

@Service
public class CaptchaServiceImpl implements CaptchaService {
	@Autowired
	private Producer producer;

	@Override
	public TextCaptcha createImgCaptcha() {
		String capText = producer.createText();
		BufferedImage bi = producer.createImage(capText);
		return new TextCaptcha(capText,bi);
	}

	@Override
	public String createStringCaptcha() {
		Random rand=new Random();
		return rand.nextInt(100000-10000) + 10000+"";
	}
}
