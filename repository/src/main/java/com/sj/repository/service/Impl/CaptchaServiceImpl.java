package com.sj.repository.service.Impl;


import java.awt.image.BufferedImage;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sj.repository.service.CaptchaService;
import com.sj.repository.util.TextCaptcha;

@Service
@Transactional
public class CaptchaServiceImpl implements CaptchaService {


	@Override
	public TextCaptcha createImgCaptcha() {
		return null;
	}

	@Override
	public String createStringCaptcha() {
		Random rand=new Random();
		return rand.nextInt(100000-10000) + 10000+"";
	}
}
