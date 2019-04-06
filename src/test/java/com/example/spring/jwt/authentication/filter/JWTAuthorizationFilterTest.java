package com.example.spring.jwt.authentication.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.spring.jwt.ApplicationTests;
import com.example.spring.jwt.authentication.dto.AuthenticationToken;
import com.example.spring.jwt.authentication.exception.HeaderTokenIllegalException;
import com.example.spring.jwt.authentication.exception.HeaderTokenNotfoundException;
import com.example.spring.jwt.authentication.properties.AuthProperties;
import com.example.spring.jwt.authentication.values.Role;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JWTAuthorizationFilterTest {

	@Autowired
	AuthProperties properties;

	AuthenticationManager manager;

	JWTAuthorizationFilter filter;

	@BeforeEach
	public void setUp() throws ServletException {

		manager = (a) -> {
			// 利用しないためダミー
			return a;
		};
	}

	@Test
	public void testFile() throws Exception {

		// テスト用のため内部鍵を使用
		properties.setPublicKeyPath(
				getClass()
						.getResource(JWTAuthorizationFilter.DEFAULT_PUBLIC_KEY_RESOURCE)
						.getFile());

		filter = new JWTAuthorizationFilter(manager, properties);
		filter.initFilterBean();

		AuthenticationToken auth = filter.authentication(ApplicationTests.TOKEN);

		assertEquals(auth.getPrincipal(), "1234567890");
		assertEquals(auth.getAuthorities().get(0).getAuthority(), Role.NONE.getAuthority());
	}

	@Test
	public void testFileException() throws Exception {

		// テスト用のため内部鍵を使用
		properties.setPublicKeyPath(
				getClass()
						.getResource(JWTAuthorizationFilter.DEFAULT_PUBLIC_KEY_RESOURCE)
						.getFile() + "***");

		filter = new JWTAuthorizationFilter(manager, properties);

		// この下の行は例外が発生するが問題ない
		assertThrows(ServletException.class, filter::initFilterBean);
	}

	@Test
	public void testDoFilter() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader(properties.getHeaderToken(), ApplicationTests.TOKEN);

		MockHttpServletResponse response = new MockHttpServletResponse();
		MockFilterChain chain = new MockFilterChain();

		// テスト用のため内部鍵を使用
		properties.setPublicKeyPath("");

		filter = new JWTAuthorizationFilter(manager, properties);
		filter.initFilterBean();

		filter.doFilter(request, response, chain);
	}

	@Test
	public void testDoFilterHeaderTokenIllegalException() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader(properties.getHeaderToken(), ApplicationTests.TOKEN_ERROR);

		MockHttpServletResponse response = new MockHttpServletResponse();
		MockFilterChain chain = new MockFilterChain();

		// テスト用のため内部鍵を使用
		properties.setPublicKeyPath("");

		filter = new JWTAuthorizationFilter(manager, properties);
		filter.initFilterBean();

		assertThrows(HeaderTokenIllegalException.class, () -> {
			filter.doFilter(request, response, chain);
		});
	}

	@Test
	public void testDoFilterHeaderTokenNotfoundException() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();

		MockHttpServletResponse response = new MockHttpServletResponse();
		MockFilterChain chain = new MockFilterChain();

		// テスト用のため内部鍵を使用
		properties.setPublicKeyPath("");

		filter = new JWTAuthorizationFilter(manager, properties);
		filter.initFilterBean();

		assertThrows(HeaderTokenNotfoundException.class, () -> {
			filter.doFilter(request, response, chain);
		});

	}

}
