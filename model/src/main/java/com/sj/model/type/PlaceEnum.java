package com.sj.model.type;

public enum PlaceEnum {
//	国产,进口;
	DOMESTIC,IMPORTED;
	public static PlaceEnum stringToEnum(String place){
		try{
		return PlaceEnum.valueOf(place);
		}catch (Exception e){
			return null;
		}
	}
}
