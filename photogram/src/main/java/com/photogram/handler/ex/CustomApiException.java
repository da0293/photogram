package com.photogram.handler.ex;

import java.util.Map;

public class CustomApiException extends RuntimeException{

	// 객체를 구분하기 위해 시리얼넘버를 넣어주는것
	// JVM을 위해 걸어주는 것 
	private static final long serialVersionUID = 1L;

	
	// 생성자로 전달된 메세지와 에러맵을 사용해 예외객체 생성
	public CustomApiException(String message) {
		super(message);
	}
	
}
