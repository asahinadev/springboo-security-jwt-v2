package com.example.spring.jwt.authentication.dto;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.example.spring.jwt.authentication.values.Role;

@SuppressWarnings("serial")
public class AuthenticationToken
		extends UsernamePasswordAuthenticationToken {

	public AuthenticationToken(
			String user,
			List<Role> authorities) {
		super(user, null, authorities);
	}

	public AuthenticationToken(String user) {
		this(user, Arrays.asList(Role.NONE));
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {
		return (List<GrantedAuthority>) super.getAuthorities();
	}
}
