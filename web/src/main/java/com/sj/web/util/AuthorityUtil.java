package com.sj.web.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

public class AuthorityUtil {
	public static Set<GrantedAuthority> createAuthority(String name) {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		GrantedAuthority authority = new GrantedAuthority() {
			public String getAuthority() {
				return name;
			}

		};
		authorities.add(authority);
		return authorities;
	}
}
