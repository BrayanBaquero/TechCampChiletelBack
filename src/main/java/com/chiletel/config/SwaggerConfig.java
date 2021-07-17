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

/**
 * <h2>Descripción:</h2>
 * Clase de configuración de swagger 3 para documentación de la api.
 * @author Brayan Baquero
 *
 */
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
				.apis(RequestHandlerSelectors.basePackage("com.chiletel"))
				//.apis(RequestHandlerSelectors.any())
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
				"Chiletel API",
				"Descripcion: Servicio que permite gestionar las visitas del equipo técnico para solucionar "
				+ "fallas en el servicio de telefonia que presenten los clientes.\n\r"
				+ "A continuación se muestra una breve descipción de la api en donde se realiza un descripción "
				+ "funcional de los enpoints a implementar y el formato de los datos a enviar y recibir junto"
				+ " con sus verbos http correspondientes.\n\rEl acceso al sistema se realiza mediante jwt"
				+ " teniendo cada endpoint protegido. Para acceder se debe en cada petición enviar el token"
				+ " en el header:\n Autorization: Bearer token",
				"1.0",
				null,
				new Contact("Brayan Baquero", null, "brayan.baquero@segurosbolibar.com"),
				null, 
				null, 
				Collections.emptyList()
				);
	}
	

   
}
