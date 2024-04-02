package com.photogram.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.photogram.config.auth.PrincipalDetails;
import com.photogram.domain.image.Image;
import com.photogram.domain.image.ImageRepository;
import com.photogram.web.dto.Image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
	
	
	private final ImageRepository imageRepository;
	
	@Value("${file.path}")
	private String uploadFolder; 
	
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		UUID uuid = UUID.randomUUID(); 
		String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename(); 
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);

		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// image객체를 만듬
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser()); 
		// image테이블에 저장 -> Image Entity를 리턴 
		Image imageEntity = imageRepository.save(image); 
		
		System.out.println(imageEntity);
	}
}
