package com.sj.model.type;

import java.util.Locale;

public enum ActivateEnum {
	DEACTIVATE, ACTIVATE,AUDIT,NOPASS;
	public static ActivateEnum fromString(String source) {
		try {
			return ActivateEnum.valueOf(source.toUpperCase(Locale.US));
		} catch (Exception e) {
			throw new IllegalArgumentException(
					String.format(
							"Invalid value '%s' for orders given! Has to be either 'index' (case insensitive).",
							source), e);
		}
	}
}
