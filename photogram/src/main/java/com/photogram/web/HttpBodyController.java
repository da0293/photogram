package com.photogram.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.photogram.domain.user.User;

@RestController
public class HttpBodyController {
	
	
	private static final Logger log = LoggerFactory.getLogger(HttpBodyController.class);

	
	@PostMapping("/body1")
	public String xwwwformurlencoded(String username) {
		log.info(username); 
		return "key=value 전송옴"; 
	}
	
	@PostMapping("/body2")
	public String textplain(@RequestBody String data) {
		log.info(data);
		return "text/plain 전송옴"; 
	}
	
	@PostMapping("/body3")
	public String applicationjson(@RequestBody String data) {
		log.info(data);
		return "json 전송옴"; 
	}
	
	@PostMapping("/body4")
	public String applicationjsonToObject(@RequestBody User user) {
		log.info(user.getUsername());
		return "json 전송옴"; 
	}
}
