package com.citybank.weather;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.citybank.weather.controller"))
          .paths(PathSelectors.any())
          .build()
          .useDefaultResponseMessages(false)
          .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
          .apiInfo(apiInfo());
    }
    
	private List<ResponseMessage> responseMessageForGET() {
        return new ArrayList<ResponseMessage>() {/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{
            add(new ResponseMessageBuilder()
                .code(500)
                .message("500 Internal Server Error")
                .build());
            add(new ResponseMessageBuilder()
                .code(404)
                .message("Not Found")
                .build());
        }};
    }
	
	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	            .title("Weather REST API")
	            .description("API for temperature query to city ")
	            .version("1.0.0")
	            .contact("Allan Tavares, allan.tavares@msn.com")
	            .build();
	}
}

