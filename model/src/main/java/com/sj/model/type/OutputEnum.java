package com.sj.model.type;

import java.util.Locale;

public enum OutputEnum {
	FIVEMILLION{public String getName(){return "五百万以下";}},
	TWENTYMILLION{public String getName(){return "五百万至两千万";}},
	FIFTYMILLION{public String getName(){return "两千万至五千万";}},
	ONEHUNDREDM{public String getName(){return "五千万至一亿";}},
	FIVEHUNDREDM{public String getName(){return "一亿至五亿";}},
	OTHER{public String getName(){return "超过五亿";}};
	
	public abstract String getName();
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
