package com.photogram.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpController {

	@GetMapping("/get")
	public String get() {
		return "<h1>Get 받음</h1>";
	}
	
	@PostMapping("/post")
	public String post() {
		return "post 받음";
	}
	
	@PutMapping("/put")
	public String put() {
		return "put 받음";
	}
	
	@DeleteMapping("/delete")
	public String delete() {
		return "delete 받음";
	}
}
