package com.sj.model.type;

public enum ProductStatusEnum {
	//下架,上架，审核中，未通过
	DOWN,UP,EXAMINE,NOT;
	public static ProductStatusEnum stringToEnum(String scale){
		try{
			return ProductStatusEnum.valueOf(scale);
		}catch(Exception e){
			return null;
		}
	}
}
