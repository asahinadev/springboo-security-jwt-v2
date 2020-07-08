package com.example.spring.jwt.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.example.spring.jwt.values.Role;

public class AuthenticationTokenTest {

	@Test
	public void test() {
		AuthenticationToken dto = new AuthenticationToken("username");
		assertEquals(dto.getName(), ("username"));
		assertEquals(dto.getAuthorities(), (Arrays.asList(Role.NONE)));
	}

}
