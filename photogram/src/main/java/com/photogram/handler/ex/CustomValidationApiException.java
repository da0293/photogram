package com.photogram.handler.ex;

import java.util.Map;

public class CustomValidationApiException extends RuntimeException{

	// 객체를 구분하기 위해 시리얼넘버를 넣어주는것
	// JVM을 위해 걸어주는 것 
	private static final long serialVersionUID = 1L;

	private Map<String, String> errorMap;
	
	public CustomValidationApiException(String message,Map<String, String> errorMap) {
		super(message);
		this.errorMap = errorMap; 
	}
	
	// 에러메세지를 담은 맵을 반환하는 메서드
	public Map<String, String> getErrorMap(){
		return errorMap;
	}
	
}
