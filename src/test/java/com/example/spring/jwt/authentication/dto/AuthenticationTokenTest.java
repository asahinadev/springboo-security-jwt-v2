package com.example.spring.jwt.authentication.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AuthenticationTokenTest {

	@Test
	public void test() {
		AuthenticationToken dto = new AuthenticationToken("");
		dto.getAuthorities();
		dto.getCredentials();
		dto.getDetails();
		dto.getName();
		dto.getPrincipal();
	}

}
