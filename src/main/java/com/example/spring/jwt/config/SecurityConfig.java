package com.example.spring.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.spring.jwt.authentication.endpoint.JWTAuthenticationEntryPoint;
import com.example.spring.jwt.authentication.filter.JWTAuthorizationFilter;
import com.example.spring.jwt.authentication.properties.AuthProperties;

@Configurable
@EnableWebSecurity
public class SecurityConfig
		extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthProperties authProperties;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);

		http.cors();

		http.csrf().disable();

		// 認証エリア設定
		http.authorizeRequests()
				.antMatchers("/error/**").permitAll()
				.antMatchers("/errors/**").permitAll()
				.anyRequest().authenticated();

		// Basic 認証は OFF
		http.httpBasic().disable();

		// Form 認証は OFF
		http.formLogin().disable();

		// JWT トークン認証は ON
		http.addFilterBefore(authenticationFilter(),
				UsernamePasswordAuthenticationFilter.class);

		// ログアウト機能は OFF
		http.logout().disable();

	}

	private BasicAuthenticationFilter authenticationFilter() throws Exception {
		BasicAuthenticationFilter filter = new JWTAuthorizationFilter(
				authenticationManager(),
				authenticationEntryPoint(),
				authProperties);
		return filter;
	}

	private AuthenticationEntryPoint authenticationEntryPoint() {
		return new JWTAuthenticationEntryPoint();
	}

}
