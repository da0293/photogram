package com.photogram.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.photogram.domain.user.User;

@RestController
public class HttpResponseJsonController {

	@GetMapping("/resp/json")
	public String respJson() {
		return "{\"username\":\"nana\"}"; 
	}
	
	@GetMapping("/resp/json/object")
	public String respJsonObject() {
		User user = new User();
		user.setUsername("홍길동");
		String data = "{\"username\":\""+user.getUsername()+"\"}";
				
		return data; 
	}
	
	@GetMapping("/resp/json/javaobject")
	public User respJsonJavaObject() {
		User user = new User();
		user.setUsername("홍길동");
		return user; // MessageConverter가 자동으로 JavaObject를 Json으로 변경해서 통신을 통해 응답한다.
	}
}
