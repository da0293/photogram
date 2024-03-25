package com.photogram.web.dto.auth;

import com.photogram.domain.user.User;

import lombok.Data;

/*
 * Dto:통신할 때 필요한 데이터를 담는 오브젝트
 */

@Data // getter,setter 포함 
public class SignupDto {
	private String username;
	private String password; 
	private String email;
	private String name; 
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
	}
}
