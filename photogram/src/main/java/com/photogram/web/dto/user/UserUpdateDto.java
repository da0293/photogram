package com.photogram.web.dto.user;

import com.photogram.domain.user.User;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateDto {
	@NotBlank
	private String name; //필수값
	@NotBlank
	private String password; //필수값
	private String website; 
	private String bio; 
	private String phone; 
	private String gender;
	
	// 필수값이 아닌값까지 고려해서 Entity를 만드는 것은 위험함 
	// 사용자가 이름,패스워드 기재 안하면 DB에 공백이 들어가므로 validation체크해야함
	public User toEntity() {
		return User.builder()
				.name(name) // Validation 체크
				.password(password) // Validation 체크
				.website(website)
				.bio(bio)
				.phone(phone)
				.gender(gender)
				.build();
	}
}
