package com.photogram.web.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.photogram.config.auth.PrincipalDetails;
import com.photogram.domain.user.User;
import com.photogram.handler.ex.CustomValidationApiException;
import com.photogram.service.UserService;
import com.photogram.web.dto.CMRespDto;
import com.photogram.web.dto.user.UserUpdateDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {
	
	private final UserService userService;
	
	// 데이터 트랜스젝션 오브젝트
	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(@PathVariable int id,
			@Valid UserUpdateDto userUpdateDto,
			BindingResult bindingResult,//꼭 @Valid가 적혀있는 다음 파라미터에 적어야함
			@AuthenticationPrincipal PrincipalDetails principalDetails){
		
		if(bindingResult.hasErrors()) {
		    // 유효성 검사 에러가 발생한 경우
			// 유효성 검사 에러를 담기 위한 'errorMap'생성
		    Map<String, String> errorMap = new HashMap<>();
		    
		    // 각 필드에 대한 에러를 errorMap에 저장
		    // bindingResult객체가 발생한 모든 에러를 순회하면서 각 필드에 대한 에러 정보 추출
		    for(FieldError error : bindingResult.getFieldErrors()) {
		        errorMap.put(error.getField(), error.getDefaultMessage());
		    }
		    
		    // 유효성 검사 실패 예외를 발생시킴
		    throw new CustomValidationApiException("유효성검사 실패함", errorMap); 
		} else {
		    // 유효성 검사 통과한 경우
		    // 회원 정보 수정 요청 처리
		    User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
		    
		    // 수정된 회원 정보를 세션에 반영
		    principalDetails.setUser(userEntity);
		    
		    // 성공 응답 반환
		    return new CMRespDto<>(1,"회원수정완료", userEntity); 
		}

		
		
	}
}
