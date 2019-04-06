package com.example.spring.jwt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.spring.jwt.authentication.properties.AuthProperties;
import com.example.spring.jwt.dto.ResponceDto;
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

		return responceDto;
	}

	private String getErrorCode(String path, Exception e) {
		String code = "E9999999";

		/** **/
		// TODO エラーコードの設定
		/** **/

		return code;
	}

}
