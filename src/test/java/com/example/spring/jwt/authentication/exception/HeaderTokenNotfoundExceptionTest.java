package com.example.spring.jwt.authentication.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HeaderTokenNotfoundExceptionTest {

	@Test
	public void testHeaderTokenNotfoundException() {
		new HeaderTokenNotfoundException();
	}

	@Test
	public void testHeaderTokenNotfoundExceptionStringThrowableBooleanBoolean() {
		new HeaderTokenNotfoundException("dummy", new Exception(), true, true);
	}

	@Test
	public void testHeaderTokenNotfoundExceptionStringThrowable() {
		new HeaderTokenNotfoundException("dummy", new Exception());
	}

	@Test
	public void testHeaderTokenNotfoundExceptionString() {
		new HeaderTokenNotfoundException("dummy");
	}

	@Test
	public void testHeaderTokenNotfoundExceptionThrowable() {
		new HeaderTokenNotfoundException(new Exception());
	}

}
