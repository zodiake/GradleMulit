package com.sj.admin.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.sj.model.model.SiteRole;

public class AuthorityUtil {
	public static Set<GrantedAuthority> createAuthority(final String name) {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		GrantedAuthority authority = new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return name;
			}
		};
		authorities.add(authority);
		return authorities;
	}

	public static Set<GrantedAuthority> createAuthority(List<SiteRole> roleName) {
		return roleName.stream()
				.map(i -> new SimpleGrantedAuthority(i.getRoleName()))
				.collect(Collectors.toSet());
	}
}
