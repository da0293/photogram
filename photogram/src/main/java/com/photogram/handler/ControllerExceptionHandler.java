package com.photogram.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.photogram.handler.ex.CustomApiException;
import com.photogram.handler.ex.CustomException;
import com.photogram.handler.ex.CustomValidationApiException;
import com.photogram.handler.ex.CustomValidationException;
import com.photogram.util.Script;
import com.photogram.web.dto.CMRespDto;

@RestController
@ControllerAdvice // 모든 exception을 낚아채는 어노테이션
public class ControllerExceptionHandler {
	// CMRespDto와 Script 비교
	// 1. 클라이언트에게 응답할 때는 Script좋음 
	// 2. Ajax통신 - CMRespDto
	// 3. Android통신 -CMREspDto 
	// 2, 3은 개발자가 응답받을 때는 코드로 받는것이 좋다. 
	
	// 자바스크립트 리턴 , CustomValidationException
	@ExceptionHandler(CustomValidationException.class)
	public String validataionException(CustomValidationException e) {
		if(e.getErrorMap()==null) {
			return Script.back(e.getMessage());
		} else {
			return Script.back(e.getErrorMap().toString()); 
		}
		
	}
	
	// CustomApiException
	@ExceptionHandler(CustomException.class)
	public String exception(CustomException e) {
		return Script.back(e.getMessage()); 
		
	}
	
	
	// 데이터 리턴 (Ajax를 통해 응답할때), CustomValidationApiException
	@ExceptionHandler(CustomValidationApiException.class)
	public ResponseEntity<?> validataionApiException(CustomValidationApiException e) {
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(),e.getErrorMap()),HttpStatus.BAD_REQUEST);
	}
	
	// CustomApiException
	@ExceptionHandler(CustomApiException.class)
	public ResponseEntity<?> apiException(CustomApiException e) {
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(),null),HttpStatus.BAD_REQUEST);
	}
}
