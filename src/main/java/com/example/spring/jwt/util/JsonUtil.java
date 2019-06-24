package com.example.spring.jwt.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

/**
 * JSON変換.
 */
public class JsonUtil {

	protected static JsonUtil INSTACE = new JsonUtil();

	private JsonUtil() {
	}

	public static final ObjectMapper MAPPER = new ObjectMapper();
	static {
		MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	@SneakyThrows
	public static String toString(Object object) {
		return MAPPER.writeValueAsString(object);
	}

	@SneakyThrows
	public static <T> T toObject(Class<T> valueType, String json) {
		return MAPPER.readValue(json, valueType);
	}

}
