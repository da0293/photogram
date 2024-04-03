package com.photogram.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Value("${file.path}")
	private String uploadFolder; // 내가 application.yml에 적은 uploadFolder경로
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		// jsp페이지에서 앞에 /upload/ 붙이면 앞을 C:/photogram/upload/ 이렇게 바꿔준다. 
		registry.addResourceHandler("/upload/**")
		.addResourceLocations("file:///" + uploadFolder)
		.setCachePeriod(60*10*6) // 1시간 
		.resourceChain(true)
		.addResolver(new PathResourceResolver());
	}

}
