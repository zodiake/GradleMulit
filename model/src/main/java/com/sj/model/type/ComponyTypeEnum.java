package com.sj.model.type;

public enum ComponyTypeEnum {
	外商独资, 合资企业, 民营企业, 国有企业, 高等院校, 刑侦机关;
	public static ComponyTypeEnum stringToEnum(String type) {
		try {
			return ComponyTypeEnum.valueOf(type);
		} catch (Exception e) {
			return null;
		}
	}
}