package com.sj.repository.util;

import java.util.UUID;

public class FileUtil {
	public static String getFileName(String name) {
		return UUID.randomUUID() + fileSuffix(name);
	}

	public static String fileSuffix(String contentType) {
		String suffix = null;
		switch (contentType) {
		case "image/jpeg":
		case "image/pjpeg":
			suffix = ".jpg";
			break;
		case "image/png":
			suffix = ".png";
			break;
		case "video/mp4":
			suffix = ".mp4";
		}
		return suffix;
	}

}
