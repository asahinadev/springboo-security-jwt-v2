package com.example.spring.jwt.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.spring.jwt.values.Status;

@ExtendWith(SpringExtension.class)
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
