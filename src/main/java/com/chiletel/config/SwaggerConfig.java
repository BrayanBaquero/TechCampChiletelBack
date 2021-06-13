package com.chiletel.config;





import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;






@Configuration
//@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer{
	
	@Bean
	public Docket api() {
		
	 return new Docket(DocumentationType.SWAGGER_2)
			 	.apiInfo(apiinfo())
			 	.securityContexts(Arrays.asList(securityContext()))
			 	.securitySchemes(Arrays.asList(apikey()))
				.select()
				//.apis(RequestHandlerSelectors.basePackage("com.chiletel"))
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiKey apikey() {
		return new ApiKey("JWT", "Authorization", "header");
	}
	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}
	private List<SecurityReference> defaultAuth(){
		AuthorizationScope authorizationScope=new AuthorizationScope("glocal", "accessEverything");
		AuthorizationScope[] authorizationScopes=new AuthorizationScope[1];
		authorizationScopes[0]=authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}

	private ApiInfo apiinfo() {
		return new ApiInfo(
				"Chiletel",
				"Descripcion",
				"2.0",
				"Terminos y condiciones",
				new Contact("Brayan Baquero", "www.contact.com", "brayan.baquero@segurosbolibar.com"),
				"Licencia", 
				"www.licencia.com", 
				Collections.emptyList()
				);
	}
	

   
}
