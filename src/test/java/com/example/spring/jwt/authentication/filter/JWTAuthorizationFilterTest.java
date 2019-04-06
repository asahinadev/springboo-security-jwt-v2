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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.spring.jwt.ApplicationTests;
import com.example.spring.jwt.authentication.dto.AuthenticationToken;
import com.example.spring.jwt.authentication.endpoint.JWTAuthenticationEntryPoint;
import com.example.spring.jwt.authentication.properties.AuthProperties;
import com.example.spring.jwt.authentication.values.Role;
import com.example.spring.jwt.dto.ResponceDto;
import com.example.spring.jwt.util.JsonUtil;
import com.example.spring.jwt.values.Status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JWTAuthorizationFilterTest {

	@Autowired
	AuthProperties properties;

	AuthenticationManager manager;
	JWTAuthorizationFilter filter;
	JWTAuthenticationEntryPoint entoryPoint;
	MockHttpServletRequest request;
	MockHttpServletResponse response;
	MockFilterChain chain = new MockFilterChain();

	@BeforeEach
	public void setUp() throws ServletException {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		chain = new MockFilterChain();
		entoryPoint = new JWTAuthenticationEntryPoint();
		manager = (a) -> {
			// デフォルトテスト
			return a;
		};

		filter = new JWTAuthorizationFilter(manager, entoryPoint, properties);
	}

	@Test
	public void testResourceOK() throws Exception {
		// 公開鍵はリソースで読み込む
		properties.setPublicKeyPath("");
		test(ApplicationTests.TOKEN);
	}

	@Test
	public void testFile() throws Exception {

		// 公開鍵はリソースから file に変換して開く
		properties.setPublicKeyPath(
				getClass().getResource(JWTAuthorizationFilter.DEFAULT_PUBLIC_KEY_RESOURCE).getFile());

		filter.initFilterBean();

		test(ApplicationTests.TOKEN);
		AuthenticationToken auth = (AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

		assertEquals(auth.getPrincipal(), "1234567890");
		assertEquals(auth.getAuthorities().get(0).getAuthority(), Role.NONE.getAuthority());
	}

	@Test
	public void testFileException() throws Exception {

		// 公開鍵は存在しないパス
		properties.setPublicKeyPath("////");

		// 例外チェック
		assertThrows(ServletException.class, () -> {
			JWTAuthorizationFilter filter = new JWTAuthorizationFilter(manager, entoryPoint, properties);
			filter.initFilterBean();
		});
	}

	@Test
	public void testTokenIllegalException() throws Exception {
		// 公開鍵はリソースで読み込む
		properties.setPublicKeyPath("");

		// 例外チェック
		test(ApplicationTests.TOKEN_ERROR);
		String value = response.getContentAsString();
		System.out.println(value);
		ResponceDto dto = JsonUtil.toObject(ResponceDto.class, value);

		// レスポンスチェック
		assertEquals(dto.getStatus(), Status.NG);
		assertEquals(dto.getMessage(), "TOKEN ILLEGAL");
		assertEquals(dto.getCode(), "E0000401");
	}

	@Test
	public void testTokenNotfoundException() throws Exception {
		// 公開鍵はリソースで読み込む
		properties.setPublicKeyPath("");

		// 例外チェック
		test(null);
		String value = response.getContentAsString();
		System.out.println(value);
		ResponceDto dto = JsonUtil.toObject(ResponceDto.class, value);

		// レスポンスチェック
		assertEquals(dto.getStatus(), Status.NG);
		assertEquals(dto.getMessage(), "TOKEN NOT FOUND");
		assertEquals(dto.getCode(), "E0000401");
	}

	private void test(String token) throws Exception {

		// リクエスト設定
		if (token != null) {
			request.addHeader(properties.getHeaderToken(), token);
		}

		filter.initFilterBean();

		filter.doFilter(request, response, chain);

	}

}
