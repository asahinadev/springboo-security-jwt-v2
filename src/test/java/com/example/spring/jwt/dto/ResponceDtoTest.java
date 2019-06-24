package com.example.spring.jwt.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.spring.jwt.values.Status;

@RunWith(SpringRunner.class)
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
