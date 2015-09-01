package com.sj.model.type;

import java.util.Locale;

public enum OutputEnum {
	ONT, TWO, THREE, FOUR, FIVE, SIX;
	public static OutputEnum formString(String source) {
		try {
			return OutputEnum.valueOf(source.toUpperCase(Locale.US));
		} catch (Exception e) {
			throw new IllegalArgumentException(
					String.format(
							"Invalid value '%s' for orders given! Has to be either 'index' (case insensitive).",
							source), e);
		}
	}
}
