package com.sj.model.type;

import java.util.Locale;

public enum ProductStatusEnum {
	// 下架,上架，审核中，未通过
	DOWN, UP, EXAMINE, NOT;
	public static ProductStatusEnum formString(String scale) {
		try {
			return ProductStatusEnum.valueOf(scale.toUpperCase(Locale.US));
		} catch (Exception e) {
			return null;
		}
	}
}