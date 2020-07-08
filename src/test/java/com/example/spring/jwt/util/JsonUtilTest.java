package com.example.spring.jwt.util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.example.spring.jwt.dto.ResponceDto;

import lombok.Getter;
import lombok.Setter;

public class JsonUtilTest {

	public static class TestDef {
		@Getter
		@Setter
		TestDef test;
	}

	@Test
	public void testToString() {

		TestDef test = new TestDef();

		JsonUtil.toString(test);
	}

	@Test
	public void testToStringError() {

		TestDef test = new TestDef();
		test.setTest(test);
		assertThrows(Exception.class, () -> {
			JsonUtil.toString(test);
		});
	}

	@Test
	public void testTo() {
		JsonUtil.toObject(ResponceDto.class, "{ \"status\" : \"OK\" }");
	}

	@Test
	public void testToObjectError() {
		assertThrows(Exception.class, () -> {
			JsonUtil.toObject(ResponceDto.class, "{");
		});
	}

}
