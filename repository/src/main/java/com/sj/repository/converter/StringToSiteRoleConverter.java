package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.model.SiteRole;

public class StringToSiteRoleConverter implements Converter<String, SiteRole> {

	@Override
	public SiteRole convert(String source) {
		try {
			return new SiteRole(Long.parseLong(source));
		} catch (Exception e) {
			return null;
		}
	}

}
