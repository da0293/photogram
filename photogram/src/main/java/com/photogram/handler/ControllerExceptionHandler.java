package com.photogram.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.photogram.handler.ex.CustomValidationException;

@RestController
@ControllerAdvice // 모든 exception을 낚아채는 어노테이션
public class ControllerExceptionHandler {
	
	@ExceptionHandler(CustomValidationException.class)
	public Map<String, String> validataionException(CustomValidationException e) {
		return e.getErrorMap();
	}
}
