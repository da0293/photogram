package com.photogram.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.photogram.domain.user.User;

@Controller
public class JavaToJspController {

	@GetMapping("/jsp/java/model")
	public String jspToJavaToModel(Model model) { // 함수의 파라미터에 Model을 선언
		User user = new User();
		user.setUsername("nana"); 
		model.addAttribute("username", user.getUsername()); // addAttribute 함수로 전달 
		return "e"; 
	}
}
