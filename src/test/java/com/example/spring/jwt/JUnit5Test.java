package com.example.spring.jwt;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("com.example.spring.jwt")
public class JUnit5Test {

	@Test
	public void testLoads() {
	}
}
