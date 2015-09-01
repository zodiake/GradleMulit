package com.sj.model.type;

import java.util.Locale;

public enum ComponyTypeEnum {
	ONE, TWO, THREE, FOUR, FIVE, SIX;
	public static ComponyTypeEnum formString(String source) {
		try {
			return ComponyTypeEnum.valueOf(source.toUpperCase(Locale.US));
		} catch (Exception e) {
			throw new IllegalArgumentException(
					String.format(
							"Invalid value '%s' for orders given! Has to be either 'index' (case insensitive).",
							source), e);
		}
	}
}