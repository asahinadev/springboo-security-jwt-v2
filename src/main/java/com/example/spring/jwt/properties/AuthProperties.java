package com.example.spring.jwt.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * 認証設定.
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app.authentication.properties")
public class AuthProperties {

	String headerToken = "x-token";

	String publicKeyPath;

	boolean userNotFound = false;

	boolean verify = true;

}
