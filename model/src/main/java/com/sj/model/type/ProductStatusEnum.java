package com.sj.model.type;

import java.util.Locale;

public enum ProductStatusEnum {
	DOWN, UP, EXAMINE, NOT;
	public static ProductStatusEnum fromString(String source) {
		try {
			return ProductStatusEnum.valueOf(source.toUpperCase(Locale.US));
		} catch (Exception e) {
			throw new IllegalArgumentException(
					String.format(
							"Invalid value '%s' for orders given! Has to be either 'index' (case insensitive).",
							source), e);
		}
	}
}
