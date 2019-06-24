package com.example.spring.jwt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.spring.jwt.service.JWTAuthorizationService;

import lombok.extern.slf4j.Slf4j;

/**
 * 認証フィルター.
 */
@Slf4j
public class JWTAuthorizationFilter
		extends BasicAuthenticationFilter {

	@Autowired
	JWTAuthorizationService authorizationService;

	/**
	 * 例外エントリを指定したコンストラクタ.
	 * 
	 * @param manager        認証管理
	 * @param entryPoint     例外エントリ
	 * @param authProperties 認証設定
	 */
	public JWTAuthorizationFilter(AuthenticationManager manager, AuthenticationEntryPoint entryPoint) {
		super(manager, entryPoint);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		log.debug("doFilterInternal({},{},{})", request, response, chain);

		try {

			String token = authorizationService.getToken(request);
			log.debug("token => {}", token);

			SecurityContextHolder.getContext().setAuthentication(authorizationService.authentication(token));
			chain.doFilter(request, response);

		} catch (AuthenticationException exception) {

			log.warn("{} {}", request.getServletPath(), exception.getMessage(), exception);

			AuthenticationEntryPoint entryPoint = getAuthenticationEntryPoint();
			if (entryPoint != null) {
				log.debug("entryPoint {}", entryPoint);
				entryPoint.commence(request, response, exception);
			}
		}
	}

}
