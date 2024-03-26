package com.photogram.web.dto.auth;

import com.photogram.domain.user.User;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/*
 * Dto:통신할 때 필요한 데이터를 담는 오브젝트
 */

@Data // getter,setter 포함 
public class SignupDto {
	@Size(max = 20)//validation 어노테이션 
	@NotBlank
	private String username;
	@NotBlank
	private String password; 
	@NotBlank
	private String email;
	@NotBlank
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
