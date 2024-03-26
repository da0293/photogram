package com.photogram.handler.ex;

import java.util.Map;

public class CustomValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private Map<String, String> errorMap;
	
	public CustomValidationException(String message,Map<String, String> errorMap) {
		super(message);
		this.errorMap = errorMap; 
	}
	
	// 에러메세지에 대한 
	public Map<String, String> getErrorMap(){
		return errorMap;
	}
	
}
