package com.sj.model.type;

public enum ProductStatusEnum {
	DOWN,UP,EXAMINE,NOT;
	public static ProductStatusEnum stringToEnum(String scale){
		try{
			return ProductStatusEnum.valueOf(scale);
		}catch(Exception e){
			return null;
		}
	}
}
