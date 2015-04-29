package com.sj.web.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class AuthorityUtil {
	public static Set<SimpleGrantedAuthority> createAuthority(final String name) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		SimpleGrantedAuthority authority=new SimpleGrantedAuthority(name);
		authorities.add(authority);
		return authorities;
	}
}
