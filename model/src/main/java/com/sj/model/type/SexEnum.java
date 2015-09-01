package com.sj.model.type;

import java.util.Locale;

public enum SexEnum {
	FEMALE,MALE;
	public static SexEnum formString(String source){
		try {
			return SexEnum.valueOf(source.toUpperCase(Locale.US));
		} catch (Exception e) {
			throw new IllegalArgumentException(
					String.format(
							"Invalid value '%s' for orders given! Has to be either 'index' (case insensitive).",
							source), e);
		}
	}
}
