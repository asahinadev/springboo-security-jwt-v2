package com.example.spring.jwt.authentication.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TokenNotfoundExceptionTest {

	@Test
	public void testHeaderTokenNotfoundException() {
		new TokenNotfoundException();
	}

	@Test
	public void testHeaderTokenNotfoundExceptionStringThrowable() {
		new TokenNotfoundException("dummy", new Exception());
	}

	@Test
	public void testHeaderTokenNotfoundExceptionString() {
		new TokenNotfoundException("dummy");
	}

	@Test
	public void testHeaderTokenNotfoundExceptionThrowable() {
		new TokenNotfoundException(new Exception());
	}

}
