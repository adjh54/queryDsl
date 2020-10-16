package com.eyeson.service.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport{
	
	/*
	 * [Library Description] wagger내의 API 그룹명이나 이동경로, 보여질 api 가 속한 패키지 등의 자세한 정보 설명
	 * 1. groupName			: Select a spec 그룹명 지정
	 * 2. apis				: Swagger에서 사용 할 패키지 경로
	 * - RequestHandlerSelectors.any() : ALL
	 * - RequestHandlerSelectors.basePackage("") : 특정 경로 지정
	 * ex).apis(RequestHandlerSelectors.basePackage("com.eyeson.service.test.controller"))
	 * 
	 * 3. paths				: Swagger에서 사용 할 URL 경로
	 * - PathSelectors.any()	: 모든 경로 
	 * - PathSelectors.ant("")	: 특정 경로 지정
	 * ex) paths(PathSelectors.ant("/api/**"))
	 * 
	 * 4. apiInfo			: ApiInfo 작성자 정보를 불러옴.
	 * 5. securitySchemes	: JWT Token과 같은 처리 
	 */
	
	@Bean
    public Docket api() {
		String groupName = "Eyes-ON API List";
		
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName(groupName)
				.select()
				.apis(RequestHandlerSelectors.any())
//				.apis(RequestHandlerSelectors.basePackage("com.eyeson.service.test.controller"))
				.paths(PathSelectors.any())
//				.paths(PathSelectors.ant("/api/**"))
				.build()
				.apiInfo(apiInfo());
    }
	
	private ApiInfo apiInfo() {
		/*
		 * [Library Description] Swagger UI 화면 설정 
		 * 1. title			: 제목
		 * 2. description 	: API 설명
		 */
		return new ApiInfoBuilder()
				.title("Eyes-ON API")
				.description("Eyes-ON API LIST")
				.version("v1")
				.build();
	}
	
	/*
	 * Spring Security 접근 권한 방지를 위한 리소스 처리
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}

