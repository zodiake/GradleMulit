package com.sj.model.type;

public enum BusinessTypeEnum {
	生产型, 贸易型, 服务型, 政府机关, 其它;
	public static BusinessTypeEnum stringToEnum(String source) {
		try {
			return BusinessTypeEnum.valueOf(source);
		} catch (Exception e) {
			return null;
		}
	}
}
