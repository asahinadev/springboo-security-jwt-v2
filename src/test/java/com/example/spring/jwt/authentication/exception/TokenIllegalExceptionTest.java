package com.example.spring.jwt.authentication.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TokenIllegalExceptionTest {

	@Test
	public void testHeaderTokenIllegalException() {
		new TokenIllegalException();
	}

	@Test
	public void testHeaderTokenIllegalExceptionStringThrowable() {
		new TokenIllegalException("dummy", new Exception());
	}

	@Test
	public void testHeaderTokenIllegalExceptionString() {
		new TokenIllegalException("dummy");
	}

	@Test
	public void testHeaderTokenIllegalExceptionThrowable() {
		new TokenIllegalException(new Exception());
	}

}
