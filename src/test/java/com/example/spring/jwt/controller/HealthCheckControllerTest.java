package com.example.spring.jwt.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.spring.jwt.ApplicationTests;
import com.example.spring.jwt.dto.ResponceDto;
import com.example.spring.jwt.properties.AuthProperties;
import com.example.spring.jwt.values.Status;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HealthCheckControllerTest {

	@Autowired
	WebApplicationContext context;

	@Autowired
	HealthCheckController controller;

	@Autowired
	AuthProperties authProperties;

	MockMvc mvc;

	ObjectMapper mapper;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.alwaysDo(log())
				.build();
		mapper = new ObjectMapper();
	}

	@Test
	public void testGet() throws Exception {
		MvcResult result = mvc.perform(get("/health")
				.header(authProperties.getHeaderToken(), ApplicationTests.TOKEN))
				.andReturn();

		ResponceDto dto = mapper.readValue(
				result.getResponse().getContentAsString(),
				ResponceDto.class);

		assertThat(dto.getStatus(), is(Status.OK));
		assertNull(dto.getCode());
		assertNull(dto.getMessage());
	}

	@Test
	public void testPost() throws Exception {
		MvcResult result = mvc.perform(post("/health")
				.header(authProperties.getHeaderToken(), ApplicationTests.TOKEN))
				.andReturn();

		ResponceDto dto = mapper.readValue(
				result.getResponse().getContentAsString(),
				ResponceDto.class);

		assertThat(dto.getStatus(), is(Status.OK));
		assertNull(dto.getCode());
		assertNull(dto.getMessage());
	}

}
