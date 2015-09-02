package com.sj.model.type;

import java.util.Locale;

public enum ComponyTypeEnum {
	FOREIGNOWNED{public String getName(){return "外商独资";}},
	JOINTVENTURE{public String getName(){return "合资企业";}},
	PRIVATE{public String getName(){return "民营企业";}},
	STATEOWNED{public String getName(){return "国有企业";}},
	UNIVERSITIES{public String getName(){return "高等院校";}},
	INVESTIGATION{public String getName(){return "刑侦机关";}};
	public abstract String getName();
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