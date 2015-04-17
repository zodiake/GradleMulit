package com.sj.repository.service.Impl;


import java.awt.image.BufferedImage;

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
	public TextCaptcha createCaptcha() {
		String capText = producer.createText();
		BufferedImage bi = producer.createImage(capText);
		return new TextCaptcha(capText,bi);
	}
}
