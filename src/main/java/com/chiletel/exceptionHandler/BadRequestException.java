package com.chiletel.exceptionHandler;

//Clase encargada de personalizar mensaje de BadRequestException, manejada por ExceptionConfig
public class BadRequestException extends RuntimeException {
	public BadRequestException() {}
	public BadRequestException(String message) {
		super(message);
	}
}
