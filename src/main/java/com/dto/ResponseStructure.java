package com.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class ResponseStructure<T> {
	
	private int statusCode;
	private String message;
	private T data;

}
