package com.example.spring.jwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.jwt.dto.ResponceDto;
import com.example.spring.jwt.values.Status;

@RestController
public class HealthCheckController {

	@RequestMapping("/health")
	public ResponceDto action() {

		ResponceDto responceDto = new ResponceDto();
		responceDto.setStatus(Status.OK);

		return responceDto;
	}
}
