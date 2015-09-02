package com.sj.model.type;

import java.util.Locale;

public enum BusinessTypeEnum {
	PRODUCTION{public String getName(){return "生产型";}},
	TRADE{public String getName(){return "贸易型";}},
	SERVICE{public String getName(){return "服务型";}},
	GOVERNMENT{public String getName(){return "政府机关";}},
	OTHER{public String getName(){return "其他";}};
	public abstract String getName();
	
	public static BusinessTypeEnum formString(String source) {
		try {
			return BusinessTypeEnum.valueOf(source.toUpperCase(Locale.US));
		} catch (Exception e) {
			throw new IllegalArgumentException(
					String.format(
							"Invalid value '%s' for orders given! Has to be either 'index' (case insensitive).",
							source), e);
		}
	}
}
