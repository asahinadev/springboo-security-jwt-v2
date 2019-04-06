package com.example.spring.jwt.authentication.properties;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
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
	}

}
