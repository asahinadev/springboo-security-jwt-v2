package com.example.spring.jwt.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.spring.jwt.values.Status;

@SpringBootTest
public class ResponceDtoTest {

	@Test
	public void test() {
		ResponceDto dto = new ResponceDto();
		dto.setStatus(Status.OK);
		dto.setCode("code");
		dto.setMessage("message");
		dto.toString();
	}

}
