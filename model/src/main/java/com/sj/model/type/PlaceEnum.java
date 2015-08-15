package com.sj.model.type;

public enum PlaceEnum {
	DOMESTIC,EUROPEANDUS,OTHER;
	public static PlaceEnum stringToEnum(String place){
		if(place.equals("国内")){
			return PlaceEnum.DOMESTIC;
		}else if(place.equals("欧美")){
			return PlaceEnum.EUROPEANDUS;
		}else{
			return PlaceEnum.OTHER;
		}
	}
}
