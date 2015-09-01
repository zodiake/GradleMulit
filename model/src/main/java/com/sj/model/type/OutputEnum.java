package com.sj.model.type;

public enum OutputEnum {
//	五百万以下,五百万两千万,两千万至五千万,五千万至一亿,一亿至五亿,五亿以上;
	ONT,TWO,THREE,FOUR,FIVE,SIX;
	public static OutputEnum stringToEnum(String output){
		try{
		return OutputEnum.valueOf(output);
		}catch (Exception e){
			return null;
		}
	}
}
