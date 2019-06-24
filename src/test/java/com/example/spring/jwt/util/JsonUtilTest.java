package com.example.spring.jwt.util;

import static org.junit.Assert.fail;

import org.junit.Test;

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

		try {
			JsonUtil.toString(test);
			fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void testTo() {
		JsonUtil.toObject(ResponceDto.class, "{ \"status\" : \"OK\" }");
	}

	@Test
	public void testToObjectError() {
		try {
			JsonUtil.toObject(ResponceDto.class, "{");
			fail();
		} catch (Exception e) {
		}
	}

}
