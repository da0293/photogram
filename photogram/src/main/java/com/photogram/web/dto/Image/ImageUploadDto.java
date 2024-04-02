package com.photogram.web.dto.Image;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageUploadDto {
	private MultipartFile file; 
	private String caption; 
	
}
