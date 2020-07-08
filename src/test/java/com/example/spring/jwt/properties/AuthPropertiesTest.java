package com.example.spring.jwt.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthPropertiesTest {

	@Autowired
	AuthProperties properties;

	@Test
	public void test() {
		AuthProperties dto = new AuthProperties();

		dto.setPublicKeyPath("PublicKeyPath");
		dto.getPublicKeyPath();

		dto.setHeaderToken("headerToken");
		dto.getHeaderToken();

		dto.setUserNotFound(false);
		dto.isUserNotFound();

		dto.setVerify(false);
		dto.isVerify();
	}

}
