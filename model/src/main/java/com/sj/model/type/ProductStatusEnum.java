package com.sj.model.type;

import java.util.Locale;

public enum ProductStatusEnum {
	DOWN, UP, EXAMINE, NOT;
	public static ProductStatusEnum stringToEnum(String scale) {
		try {
			return ProductStatusEnum.valueOf(scale.toUpperCase(Locale.US));
		} catch (Exception e) {
			return null;
		}
	}
}
