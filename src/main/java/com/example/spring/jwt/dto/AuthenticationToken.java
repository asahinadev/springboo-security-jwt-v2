package com.example.spring.jwt.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.example.spring.jwt.values.Role;
import com.nimbusds.jwt.JWTClaimsSet;

/**
 * 認証情報保管用.
 */
@SuppressWarnings("serial")
public class AuthenticationToken
		extends UsernamePasswordAuthenticationToken {

	JWTClaimsSet claims;

	/**
	 * コンストラクタ.
	 * 
	 * @param user ログインユーザー識別子.
	 * @param password パスワード
	 * @param authorities 権限
	 * @param claims ユーザー情報 (OpenID)
	 */
	public AuthenticationToken(String user, String password, List<Role> authorities, JWTClaimsSet claims) {
		super(user, password, authorities);
		Objects.requireNonNull(claims, "claims requireNonNull");
		this.claims = claims;
	}

	/**
	 * コンストラクタ（role = NONE ).
	 * 
	 * @param user ログインユーザー識別子.
	 */
	public AuthenticationToken(String user) {
		this(user, null, Arrays.asList(Role.NONE), new JWTClaimsSet.Builder().build());
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {
		return Collections.unmodifiableList(new ArrayList<>(super.getAuthorities()));
	}

}
