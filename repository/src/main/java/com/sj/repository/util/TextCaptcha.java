package com.sj.repository.util;

import java.awt.image.BufferedImage;

public class TextCaptcha {

	private String text;
	private BufferedImage img;

	public TextCaptcha(String text, BufferedImage img) {
		this.img = img;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

}
