package com.chiletel.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.chiletel.security.service.UserDetailsServiceImpl;

/*
 * Se ejecuta por cada peticion, comprueba si es valido el token usando el JWtProvider
 * en caso de que sea valido el token permitira el acceso al recurso, en caso contrario
 * lanzara una exepcion.
 * Filtro se ejecutara una vez por cada peticion
 */
 
public class JwtTokenFilter extends OncePerRequestFilter{
	
	private final static Logger logger=LoggerFactory.getLogger(JwtTokenFilter.class);
	
	@Autowired
	JwtProvider jwtProvider;
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token=getToken(request);
			if(token!=null && jwtProvider.validateToke(token)) {//Comprovar que el token es valido y que exista
				String nombreUsuario=jwtProvider.getNombreUsuarioFromToken(token);//A partir del token obtener el usuario
				UserDetails userDetails=userDetailsServiceImpl.loadUserByUsername(nombreUsuario); //Se crea un user details
				UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());//Autenticar usuario
				SecurityContextHolder.getContext().setAuthentication(auth);//Pasarlo al contexto de autenticacion
			}
			
		} catch (Exception e) {
			logger.error("Fallo el metodo doFilter");
		}
		filterChain.doFilter(request, response);
		
	}
	
	//Extraer el token Eliminar bearer
	private String getToken(HttpServletRequest request) {
		String header=request.getHeader("authorization");
		
		System.out.println(header);
		if(header!=null && header.startsWith("Bearer")) {
			return header.replace("Bearer ", "");
		}else {
			return null;
		}
	}

}
