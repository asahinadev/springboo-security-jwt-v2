package com.example.spring.jwt.authentication.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app.authentication.properties")
public class AuthProperties {


	String headerToken = "x-token";

	String publicKeyPath;

}
