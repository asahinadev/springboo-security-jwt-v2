package com.example.spring.jwt.authentication.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HeaderTokenIllegalExceptionTest {

	@Test
	public void testHeaderTokenIllegalException() {
		new HeaderTokenIllegalException();
	}

	@Test
	public void testHeaderTokenIllegalExceptionStringThrowableBooleanBoolean() {
		new HeaderTokenIllegalException("dummy", new Exception(), true, true);
	}

	@Test
	public void testHeaderTokenIllegalExceptionStringThrowable() {
		new HeaderTokenIllegalException("dummy", new Exception());
	}

	@Test
	public void testHeaderTokenIllegalExceptionString() {
		new HeaderTokenIllegalException("dummy");
	}

	@Test
	public void testHeaderTokenIllegalExceptionThrowable() {
		new HeaderTokenIllegalException(new Exception());
	}

}
