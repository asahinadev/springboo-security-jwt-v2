package com.example.spring.jwt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.jwt.dto.ResponceDto;
import com.example.spring.jwt.values.Status;

import lombok.extern.slf4j.Slf4j;

/**
 * ヘルスチェック.
 */
@Slf4j
@RestController
public class HealthCheckController {

	/**
	 * メイン.
	 * 
	 * @param request リクエスト情報
	 * @return レスポンス
	 */
	@RequestMapping("/health")
	public ResponceDto action(HttpServletRequest request) {

		log.debug("{} /health", request.getMethod());

		ResponceDto responceDto = new ResponceDto();
		responceDto.setStatus(Status.OK);

		return responceDto;
	}
}
