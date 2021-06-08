package com.chiletel.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

//Se encarga de verificar si el token es valido , si no retornada respuesta 401 no autorizado
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint{
	
	private final static Logger logger=LoggerFactory.getLogger(JwtEntryPoint.class);
	//Rechaza todas la peticiones que no esten autenticadas
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.error("Error: "+authException.getMessage());
		logger.error("Error: "+authException.getCause());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"No Autorizado");
		
	}
	
}
