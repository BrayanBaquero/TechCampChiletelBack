package com.chiletel.exceptionHandler;


//Clase encargada de personalizar mensaje de NotFoundException, manejada por ExceptionConfig
public class NotFoundException extends RuntimeException {
	
	public NotFoundException() {}
	
	public NotFoundException(String message) {
		super(message);
	}
}
