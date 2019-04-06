package com.example.spring.jwt.authentication.values;

import org.springframework.security.core.GrantedAuthority;

/**
 * 権限情報.
 */
public enum Role implements GrantedAuthority {
	GROUP, USER, NONE;

	@Override
	public String getAuthority() {
		return name();
	}

}
