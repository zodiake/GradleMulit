package com.sj.model.type;

import java.util.Locale;

public enum ActivateEnum {
	DEACTIVATE, ACTIVATE;
	public static ActivateEnum fromString(String state) {
		try {
			return ActivateEnum.valueOf(state.toUpperCase(Locale.US));
		} catch (Exception e) {
			return null;
		}
	}
}
