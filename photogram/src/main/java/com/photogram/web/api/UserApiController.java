package com.photogram.web.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.photogram.config.auth.PrincipalDetails;
import com.photogram.domain.user.User;
import com.photogram.service.UserService;
import com.photogram.web.dto.CMRespDto;
import com.photogram.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {
	
	private final UserService userService;
	
	// 데이터 트랜스젝션 오브젝트
	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(@PathVariable int id,UserUpdateDto userUpdateDto
			,@AuthenticationPrincipal PrincipalDetails principalDetails){
		System.out.println(userUpdateDto);
		User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
		principalDetails.setUser(userEntity);// 세션정보 변경
		return new CMRespDto<>(1,"회원수정완료", userEntity); 
	}
}
