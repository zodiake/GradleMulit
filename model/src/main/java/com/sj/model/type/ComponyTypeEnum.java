package com.sj.model.type;

public enum ComponyTypeEnum {
	ONE,TWO,THREE,FOUR,FIVE,SIX;
	public static ComponyTypeEnum stringToEnum(String type) {
		try {
			return ComponyTypeEnum.valueOf(type);
		} catch (Exception e) {
			return null;
		}
	}
}