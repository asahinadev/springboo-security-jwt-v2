package com.example.spring.jwt.dto;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.spring.jwt.values.Role;

@RunWith(SpringRunner.class)
public class AuthenticationTokenTest {

	@Test
	public void test() {
		AuthenticationToken dto = new AuthenticationToken("username");
		assertThat(dto.getName(), equalTo("username"));
		assertThat(dto.getAuthorities(), is(Arrays.asList(Role.NONE)));
	}

}
