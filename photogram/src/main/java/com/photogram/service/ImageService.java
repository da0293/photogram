package com.photogram.service;

import org.springframework.stereotype.Service;

import com.photogram.config.auth.PrincipalDetails;
import com.photogram.domain.image.ImageRepository;
import com.photogram.web.dto.Image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
	
	private final ImageRepository imageRepository; 
	
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		
	}
}
