package com.example.spring.jwt.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.example.spring.jwt.ApplicationTests;
import com.example.spring.jwt.dto.AuthenticationToken;
import com.example.spring.jwt.service.JWTAuthorizationService;
import com.example.spring.jwt.values.Role;

@SpringBootTest
public class JWTAuthorizationFilterTest {

	MockHttpServletRequest request;
	MockHttpServletResponse response;

	MockFilterChain chain = new MockFilterChain();

	@Autowired
	JWTAuthorizationService authorizationService;

	@BeforeEach
	public void setUp() throws ServletException {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test
	public void testOK() throws Exception {
		AuthenticationToken auth = test(ApplicationTests.TOKEN);
		assertEquals(auth.getPrincipal(), "1234567890");
		assertEquals(auth.getAuthorities().get(0).getAuthority(), Role.USER.getAuthority());
		assertEquals(((Role) auth.getAuthorities().get(0)).getAuthorities(), Role.USER.getAuthorities());
	}

	@Test
	public void testTokenIllegalException() throws Exception {
		assertThrows(Exception.class, () -> {
			test(ApplicationTests.TOKEN_ERROR);
		});
	}

	@Test
	public void testTokenNotfoundException() throws Exception {
		assertThrows(Exception.class, () -> {
			test(null);
		});
	}

	private AuthenticationToken test(String token) throws Exception {
		return authorizationService.authentication(token);

	}

}
