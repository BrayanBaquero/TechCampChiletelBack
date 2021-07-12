package com.chiletel.exceptionHandler;

/**
 * <h2>Descripción:</h2>
 * Clase encargada de personalizar mensaje de BadRequestException, manejada por {@link ExceptionConfig}
 * @author Brayan Baquero
 */
public class BadRequestException extends RuntimeException {
	public BadRequestException() {}
	public BadRequestException(String message) {
		super(message);
	}
}
