package com.example.spring.jwt.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.spring.jwt.dto.ResponceDto;
import com.example.spring.jwt.values.Status;

@RunWith(SpringRunner.class)
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

		assertThat(dto.getStatus(), equalTo(Status.NG));
		assertThat(dto.getMessage(), equalTo(exception.getMessage()));
		assertThat(dto.getCode(), equalTo(code));
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
