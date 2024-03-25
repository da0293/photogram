package com.photogram.web.dto.auth;

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
}
