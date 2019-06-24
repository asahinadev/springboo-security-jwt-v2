package com.example.spring.jwt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.spring.jwt.dto.ResponceDto;
import com.example.spring.jwt.properties.AuthProperties;
import com.example.spring.jwt.values.Status;

/**
 * 共通エラー判定用.
 */
@ControllerAdvice
public class ErrorController {

	@Autowired
	AuthProperties authProperties;

	/**
	 * 408 例外ハンドリング.
	 * 
	 * @param request リクエスト情報
	 * @param e       例外
	 * @return レスポンス
	 */
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public ResponceDto defaultExceptionHandler(HttpServletRequest request, Exception e) {

		ResponceDto responceDto = new ResponceDto();
		responceDto.setStatus(Status.NG);
		responceDto.setCode(getErrorCode(request.getServletPath(), e));
		responceDto.setMessage(e.getMessage());

		return responceDto;
	}

	private String getErrorCode(String path, Exception e) {
		String code = "E9999999";

		switch (path) {
			case "/start":
				if (BadCredentialsException.class.isInstance(e)) {
					code = "E2000401";
				} else {
					code = "E2000500";
				}
				break;

			default:
				break;

		}

		return code;
	}

}
