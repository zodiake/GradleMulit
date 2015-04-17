package com.sj.web.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.sj.web.util.AuthorityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sj.model.model.SiteUser;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.service.SiteUserService;

@Service("authService")
public class DefaultUserService implements UserDetailsService {
	@Autowired
	private SiteUserService userService;

	public UserDetails loadUserByUsername(String name) {
		SiteUser user = userService.findByNameAndEnabled(name,
				ActivateEnum.ACTIVATE);
		if (user == null)
			throw new UsernameNotFoundException("user not exist");
		return new SiteUserDetails(user);
	}

	private class SiteUserDetails extends SiteUser implements UserDetails {
		public SiteUserDetails(SiteUser user) {
			setId(user.getId());
			setName(user.getName());
			setPassword(user.getPassword());
			setEnabled(user.getEnabled());
			setSiteAuthority(user.getSiteAuthority());
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return AuthorityUtil.createAuthority(getSiteAuthority());
		}

		@Override
		public String getUsername() {
			return getName();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			if (getEnabled().equals(ActivateEnum.ACTIVATE))
				return true;
			else
				return false;
		}

	}
}
