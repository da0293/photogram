package com.photogram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 파일을 리턴할것이기때문에 @Controller
public class HttpRespController {
	
	// 일반 정적파일은 resource/static폴더내부가 디폴트경로
	// 프레임워크 사용(틀이 이미 정해져 있음)-
	@GetMapping("/txt")
	public String txt() {
		return "a.txt";
	}
	
	
	// /static/b.mustache 
	// 자바코드 해석을 안함-다운로드 받아져버림(사용안하는 방법)
	@GetMapping("/mus1")
	public String mus1(){
		return "b.mustache"; 
	}
	
	// /template/b.mustache
	// 머스테치 템플릿 엔진 라이브러리 등록 완료 
	// templates 폴더 안에 .mustache을 나두면 확장자 없이 파일명만 적으면 자동으로 찾아감
	@GetMapping("/mus2")
	public String mus2(){
		return "b"; 
	}
	
	@GetMapping("/jsp")
	public String jsp(){
		return "c"; 
	}
}
