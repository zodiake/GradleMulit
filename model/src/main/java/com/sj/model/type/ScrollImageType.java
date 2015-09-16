package com.sj.model.type;

import java.util.Locale;

public enum ScrollImageType {
	INDEX;
	public static ScrollImageType fromString(String value) {

		try {
			return ScrollImageType.valueOf(value.toUpperCase(Locale.US));
		} catch (Exception e) {
			throw new IllegalArgumentException(
					String.format(
							"Invalid value '%s' for orders given! Has to be either 'index' (case insensitive).",
							value), e);
		}
	}
}
