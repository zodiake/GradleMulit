package com.sj.model.type;

public enum SexEnum {
	FEMALE,MALE;
	public static SexEnum stringToEnum(String scale){
		try{
			return SexEnum.valueOf(scale);
		}catch(Exception e){
			return null;
		}
	}
}
