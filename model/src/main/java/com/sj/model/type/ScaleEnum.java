package com.sj.model.type;

public enum ScaleEnum {
	五十人以下,五十到一百人,一百到五百,超过五百;
	public static ScaleEnum stringToEnum(String scale){
		try{
			return ScaleEnum.valueOf(scale);
		}catch(Exception e){
			return null;
		}
	}
}
