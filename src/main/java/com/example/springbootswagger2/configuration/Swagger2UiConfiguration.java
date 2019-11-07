package com.example.springbootswagger2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.common.base.Predicates;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2UiConfiguration extends WebMvcConfigurerAdapter  {
	@Bean
	public Docket api() {
		// @formatter:off
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				//.apis(RequestHandlerSelectors.any())
				//.apis(RequestHandlerSelectors.basePackage("com.example.controller"))
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				//.paths(PathSelectors.any())
				//.paths(PathSelectors.ant("/foos/*"))
				//.paths(PathSelectors.ant("/swagger2-demo"))
				.build()
				.apiInfo(apiInfo());
		// @formatter:on
	}
	
	//enabling swagger-ui part for visual documentation
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	// This method is used to customize the information on the swagger UI
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "My REST API", 
	      "Some custom description of API.", 
	      "API TOS", 
	      "Terms of service", 
	      new Contact("Rajesh Rana", "www.mycustom.com", "rajesh.rana@synechron.com"), 
	      "License of API", "API license URL");
	}

}
