package com.photogram.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

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
	@ExceptionHandler(CustomValidationException.class)
	public String validataionException(CustomValidationException e) {
		return Script.back(e.getErrorMap().toString()); 
	}
}
