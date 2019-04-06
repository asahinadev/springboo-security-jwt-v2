package com.example.spring.jwt.authentication.dto;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.example.spring.jwt.authentication.values.Role;

/**
 * 認証情報保管用.
 */
@SuppressWarnings("serial")
public class AuthenticationToken
		extends UsernamePasswordAuthenticationToken {

	/**
	 * コンストラクタ（role = 指定).
	 * 
	 * @param user        ログインユーザー識別子.
	 * @param authorities 権限一覧
	 */
	public AuthenticationToken(String user, List<Role> authorities) {
		super(user, null, authorities);
	}

	/**
	 * コンストラクタ（role = NONE ).
	 * 
	 * @param user ログインユーザー識別子.
	 */
	public AuthenticationToken(String user) {
		this(user, Arrays.asList(Role.NONE));
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {
		return (List<GrantedAuthority>) super.getAuthorities();
	}
}
