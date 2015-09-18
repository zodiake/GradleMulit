package com.sj.model.type;

import java.util.Locale;

public enum ActivateEnum {
	DEACTIVATE, ACTIVATE, AUDIT, NOPASS;
	public static ActivateEnum fromString(String source) {
		try {
			return ActivateEnum.valueOf(source.toUpperCase(Locale.US));
		} catch (Exception e) {
			return null;
		}
	}
}
