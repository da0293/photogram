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
	
	// 업로드된 파일에 접근하기 위한 ResourceHandler를 설정합니다.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    // 기본 ResourceHandler를 추가합니다.
	    WebMvcConfigurer.super.addResourceHandlers(registry);
	    
	    // JSP 페이지에서 /upload/ 경로를 사용하여 접근하면 해당 경로를 실제 파일 시스템의 업로드 폴더로 매핑합니다.
	    registry.addResourceHandler("/upload/**")
	            .addResourceLocations("file:///" + uploadFolder) // 실제 파일 시스템 경로를 URL로 설정합니다.
	            .setCachePeriod(60 * 10 * 6) // 캐시 기간을 설정합니다. (1시간)
	            .resourceChain(true) // 리소스 체인을 활성화합니다.
	            .addResolver(new PathResourceResolver()); // 요청된 리소스를 실제 파일 시스템에서 찾아내는 리졸버를 추가합니다.
	}


}
