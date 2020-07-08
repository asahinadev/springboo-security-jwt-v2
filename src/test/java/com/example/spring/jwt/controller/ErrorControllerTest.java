package com.example.spring.jwt.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;

import com.example.spring.jwt.dto.ResponceDto;
import com.example.spring.jwt.values.Status;

@SpringBootTest
public class ErrorControllerTest {

	@Autowired
	ErrorController controller;

	private ResponceDto call(String path, Exception exception) {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setServletPath(path);
		return controller.defaultExceptionHandler(request, exception);
	}

	private void test(ResponceDto dto, String code, Exception exception) {

		assertEquals(dto.getStatus(), (Status.NG));
		assertEquals(dto.getMessage(), (exception.getMessage()));
		assertEquals(dto.getCode(), (code));
	}

	@Test
	public void testStartBadCredentialsException() {
		BadCredentialsException exception = new BadCredentialsException("BadCredentialsException");
		ResponceDto dto = call("/start", exception);
		test(dto, "E2000401", exception);
	}

	@Test
	public void testStartOtherException() {
		Exception exception = new Exception("OtherException");
		ResponceDto dto = call("/start", exception);
		test(dto, "E2000500", exception);
	}

	@Test
	public void testOtherPage() {
		Exception exception = new Exception("OtherException");
		ResponceDto dto = call("/other", exception);
		test(dto, "E9999999", exception);
	}
}
