package com.example.spring.jwt.util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.spring.jwt.dto.ResponceDto;

import lombok.Getter;
import lombok.Setter;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class JsonUtilTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	public static class TestDef {
		@Getter
		@Setter
		TestDef test;
	}

	@Test
	void testToStringError() {

		TestDef test = new TestDef();
		test.setTest(test);

		assertThrows(Exception.class, () -> {
			JsonUtil.toString(test);
		});
	}

	@Test
	void testToObjectError() {
		assertThrows(Exception.class, () -> {
			JsonUtil.toObject(ResponceDto.class, "{");
		});
	}

}
