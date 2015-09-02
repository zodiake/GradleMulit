package com.sj.model.type;

import java.util.Locale;

public enum ScaleEnum {
	FIFTY{public String getName(){return "五十人以下";}},
	ONEHUNDRED{public String getName(){return "五十至一百人";}},
	FIVEHUNDRED{public String getName(){return "一百至五百人";}},
	OTHER{public String getName(){return "超过五百人";}};
	
	public abstract String getName();
	
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
