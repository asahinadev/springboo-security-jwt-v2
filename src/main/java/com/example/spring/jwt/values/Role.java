package com.example.spring.jwt.values;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

/**
 * 権限情報.
 */
public enum Role implements GrantedAuthority {
	SYSTEM, ADMIN, GROUP, USER, NONE;

	@Override
	public String getAuthority() {
		return name();
	}

	public List<Role> getAuthorities() {
		switch (this) {

			case SYSTEM:
				// 下位権限所有
				return Arrays.asList(this, USER, GROUP, SYSTEM);

			case ADMIN:
				// 下位権限所有
				return Arrays.asList(this, USER, GROUP);

			case GROUP:
				// 下位権限所有
				return Arrays.asList(this, USER);

			default:
				// 基本は自分ユーザーのみ
				return Arrays.asList(this);
		}

	}

}
