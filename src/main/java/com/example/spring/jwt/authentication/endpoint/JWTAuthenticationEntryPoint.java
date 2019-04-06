package com.example.spring.jwt.authentication.endpoint;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.example.spring.jwt.authentication.exception.TokenIllegalException;
import com.example.spring.jwt.authentication.exception.TokenNotfoundException;
import com.example.spring.jwt.dto.ResponceDto;
import com.example.spring.jwt.util.JsonUtil;
import com.example.spring.jwt.values.Status;

import lombok.extern.slf4j.Slf4j;

/**
 * 認証例外処理用.
 */
@Slf4j
public class JWTAuthenticationEntryPoint
		implements AuthenticationEntryPoint {

	@Override
	public void commence(
			HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		log.debug(authException.getMessage(), authException);

		ResponceDto dto = new ResponceDto();
		dto.setStatus(Status.NG);

		/** **/
		// TODO エラーコードの設定
		dto.setCode("E0000401");
		dto.setMessage("UNAUTHORIZED");

		if (authException instanceof TokenNotfoundException) {
			dto.setCode("E0000401");
			dto.setMessage("TOKEN NOT FOUND");
		} else if (authException instanceof TokenIllegalException) {
			dto.setCode("E0000401");
			dto.setMessage("TOKEN ILLEGAL");
		}
		/** **/

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		try (PrintWriter out = response.getWriter()) {

			String json = JsonUtil.toString(dto);

			out.println(json);

		}
		response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
	}

}
