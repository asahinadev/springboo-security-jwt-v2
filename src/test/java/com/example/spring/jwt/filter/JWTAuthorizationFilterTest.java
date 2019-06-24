package com.example.spring.jwt.filter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.spring.jwt.ApplicationTests;
import com.example.spring.jwt.dto.AuthenticationToken;
import com.example.spring.jwt.service.JWTAuthorizationService;
import com.example.spring.jwt.values.Role;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JWTAuthorizationFilterTest {

	MockHttpServletRequest request;
	MockHttpServletResponse response;

	MockFilterChain chain = new MockFilterChain();

	@Autowired
	JWTAuthorizationService authorizationService;

	@Before
	public void setUp() throws ServletException {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test
	public void testOK() throws Exception {
		AuthenticationToken auth = test(ApplicationTests.TOKEN);
		assertThat(auth.getPrincipal(), equalTo("1234567890"));
		assertThat(auth.getAuthorities().get(0).getAuthority(), is(Role.USER.getAuthority()));
		assertThat(((Role) auth.getAuthorities().get(0)).getAuthorities(), is(Role.USER.getAuthorities()));
	}

	@Test(expected = Exception.class)
	public void testTokenIllegalException() throws Exception {
		// 例外チェック
		test(ApplicationTests.TOKEN_ERROR);
	}

	@Test(expected = Exception.class)
	public void testTokenNotfoundException() throws Exception {
		// 例外チェック
		test(null);
	}

	private AuthenticationToken test(String token) throws Exception {
		return authorizationService.authentication(token);

	}

}
