package com.sj.repository.service;

import com.sj.repository.util.TextCaptcha;

public interface CaptchaService {
	public TextCaptcha createImgCaptcha();
	public String createStringCaptcha();
}
