package com.chiletel.exceptionHandler;
 /**
  * <2>Descripción:</h2>
  * Clase encargada de personalizar mensaje de NotFoundException, manejada por {@link ExceptionConfig}
  * @author Brayan Baquero
  */
public class NotFoundException extends RuntimeException {
	
	public NotFoundException() {}
	
	public NotFoundException(String message) {
		super(message);
	}
}
