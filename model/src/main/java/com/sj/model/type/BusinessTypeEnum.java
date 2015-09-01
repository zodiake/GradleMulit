package com.sj.model.type;

import java.util.Locale;

public enum BusinessTypeEnum {
	ONE, TWO, THREE, FOUR, FIVE;
	public static BusinessTypeEnum formString(String source) {
		try {
			return BusinessTypeEnum.valueOf(source.toUpperCase(Locale.US));
		} catch (Exception e) {
			throw new IllegalArgumentException(
					String.format(
							"Invalid value '%s' for orders given! Has to be either 'index' (case insensitive).",
							source), e);
		}
	}
}
