package com.sj.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.sj.model.model.SiteUser;

@Service
public class SiteUserContext implements UserContext {
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public SiteUser getCurrnetUser() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		return (SiteUser) authentication.getPrincipal();
	}

	@Override
	public void setCurrentUser(SiteUser user) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(user
				.getName());
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				userDetails, user.getPassword(), userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	@Override
	public boolean isLogin() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication.getPrincipal() instanceof String) {
			String principal = (String) authentication.getPrincipal();
			if (principal.equals("anonymousUser")) {
				return false;
			}
		}
		return true;
	}

}
