package com.example.spring.jwt.dto;

import java.io.Serializable;

import com.example.spring.jwt.values.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * レスポンス.
 */
@Getter
@Setter
@SuppressWarnings("serial")
@JsonInclude(content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponceDto implements Serializable {

	@JsonProperty("status")
	Status status;

	@JsonProperty("message")
	String message;

	@JsonProperty("code")
	String code;
}
