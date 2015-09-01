package com.sj.model.type;

import java.util.Locale;

public enum ScaleEnum {
	ONE, TWO, THREE, FOUR;
	public static ScaleEnum formString(String source) {
		try {
			return ScaleEnum.valueOf(source.toUpperCase(Locale.US));
		} catch (Exception e) {
			throw new IllegalArgumentException(
					String.format(
							"Invalid value '%s' for orders given! Has to be either 'index' (case insensitive).",
							source), e);
		}
	}
}
