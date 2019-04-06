package com.example.spring.jwt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.spring.jwt.authentication.exception.HeaderTokenIllegalException;
import com.example.spring.jwt.authentication.exception.HeaderTokenNotfoundException;
import com.example.spring.jwt.authentication.properties.AuthProperties;
import com.example.spring.jwt.dto.ResponceDto;
import com.example.spring.jwt.values.Status;

@ControllerAdvice
public class ErrorController {

	@Autowired
	AuthProperties authProperties;

	@ResponseStatus(code = HttpStatus.OK)
	@ExceptionHandler(HeaderTokenNotfoundException.class)
	public ResponceDto exceptionHandler(HttpServletRequest request, HeaderTokenNotfoundException e) {
		return defaultExceptionHandler(request, e);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@ExceptionHandler(HeaderTokenIllegalException.class)
	public ResponceDto exceptionHandler(HttpServletRequest request, HeaderTokenIllegalException e) {
		return defaultExceptionHandler(request, e);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@ExceptionHandler(Exception.class)
	public ResponceDto defaultExceptionHandler(HttpServletRequest request, Exception e) {
		ResponceDto responceDto = new ResponceDto();
		responceDto.setStatus(Status.NG);
		responceDto.setCode(getErrorCode(request.getServletPath(), e));
		return responceDto;
	}

	private String getErrorCode(String path, Exception e) {
		String code = "E9999999";
		// TODO èàóùÇ…ÇÊÇ¡Çƒé¿ëï
		return code;
	}

}
