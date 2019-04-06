package com.example.spring.jwt.authentication.values;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	GROUP, USER, NONE;

	@Override
	public String getAuthority() {
		return name();
	}

}
