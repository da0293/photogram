package com.photogram.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.photogram.config.auth.PrincipalDetails;
import com.photogram.handler.ex.CustomValidationException;
import com.photogram.service.ImageService;
import com.photogram.web.dto.Image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ImageController {
	
	private final ImageService imageService; 
	
	@GetMapping({"/", "/image/story"})
	public String story() {
		return "image/story";
	}
	
	@GetMapping("/image/popular")
	public String popular() {
		return "image/popular";
	}
	
	@GetMapping("/image/upload")
	public String upload() {
		return "image/upload";
	}
	
	@PostMapping("/image")
	public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {

		if(imageUploadDto.getFile().isEmpty()) {
			// 데이터가 아니라 페이지를 응답 
			// ControllerExceptionHandler의 validataionException이 발동 안함 why?
			// 
			throw new CustomValidationException("이미지가 첨부되지 않았습니다.", null);
			// 
		}
		// 서비스 호출
		imageService.사진업로드(imageUploadDto, principalDetails);
		return "redirect:/user/" + principalDetails.getUser().getId(); 
	}
}
