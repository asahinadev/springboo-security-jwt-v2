package com.example.spring.jwt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

/**
 * リクエスト、レスポンスをキャッシュ可能にする.
 */
@Component
public class HttpServletWrapperFilter
		extends GenericFilterBean
		implements OrderedFilter {

	@Override
	public void doFilter(
			ServletRequest request,
			ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {

		ContentCachingRequestWrapper cachRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
		ContentCachingResponseWrapper cachResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);

		chain.doFilter(cachRequest, cachResponse);

	}

	@Override
	public int getOrder() {
		return 0;
	}

}
