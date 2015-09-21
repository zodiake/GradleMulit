package com.sj.repository.search.model;

import java.util.Locale;

public enum SortEnum {
	PRICEDESC, PRICEASC, REVIEWDESC, REVIEWASC, CREATEDTIMEDESC, CREATEDTIMEASC;

	public static SortEnum fromString(String source) {
		try {
			return SortEnum.valueOf(source.toUpperCase(Locale.US));
		} catch (Exception e) {
			return null;
		}
	}
}
