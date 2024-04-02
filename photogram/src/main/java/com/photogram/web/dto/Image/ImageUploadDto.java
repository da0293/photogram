package com.photogram.web.dto.Image;

import org.springframework.web.multipart.MultipartFile;

import com.photogram.domain.image.Image;
import com.photogram.domain.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageUploadDto {
	private MultipartFile file; 
	private String caption;
	public Image toEntity(String postImageUrl, User user) {
		return Image.builder()
				.caption(caption)
				.postImageUrl(postImageUrl)
				.user(user)
				.build();
	} 
	
}
