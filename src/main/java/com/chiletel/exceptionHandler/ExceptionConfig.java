package com.chiletel.exceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * <h2>Descripción:</h2>
 * Clase encargada de manejar de forma global las excepciones de la api
 * exponiendo mediente un {@link ResponseEntity} el error presentado al 
 * usuario de la api. <br>
 * La respuesta va tener el formato definido en la clase {@link ExceptionResponse} 
 * @author Brayan Baquero
 *
 */

//Clase encargada de controllar excepciones de forma global.
@ControllerAdvice
public class ExceptionConfig {
	
	/**
	 * <h2>Descripción:</h2>
	 * Excepción personalizada para NotFound.
	 * @param e
	 * @return {@link ExceptionResponse}
	 */
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ExceptionResponse> notFoundException(Exception e){
		ExceptionResponse response = new ExceptionResponse();
        response.setCode("NOT_FOUND");
        response.setStatus(404);
        response.setMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.NOT_FOUND);
	}
	
	/**
	 * <h2>Descripción:</h2>
	 * Excepción personalizada para BadRequest.
	 * @param e
	 * @return {@link ExceptionResponse}
	 */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ExceptionResponse> badRequestException(Exception e){
		ExceptionResponse response = new ExceptionResponse();
        response.setCode("BAD_REQUEST");
        response.setStatus(400);
        response.setMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
	}
	//@ExceptionHandler(NoSuchElementException.class)
	//@ExceptionHandler(OracleDatabaseException.class)
	/**
	 * <h2>Descripción:</h2>
	 * Manejo de excepción cuando el valor de las fechas recibidas en un requestParam es null
	 * @param e
	 * @return {@link ExceptionResponse}
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ExceptionResponse> requestParamPresent(Exception err){
		ExceptionResponse response = new ExceptionResponse();
        response.setCode("BAD_REQUEST");
        response.setMessage(err.getMessage());
        response.setStatus(400);
        response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * <h2>Descripción:</h2>
	 * Manejo de excepción cuando se insertan fechas con mal formato en un RequestParam
	 * @param e
	 * @return {@link ExceptionResponse}
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ExceptionResponse> invalidFormatDate(Exception err){
		ExceptionResponse response = new ExceptionResponse();
        response.setCode("BAD_REQUEST");
        response.setMessage(err.getMessage());
        response.setStatus(400);
        response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * <h2>Descripción:</h2>
	 * Manejo de excepción cuando se detectan datos que no cumplen con los<br>
	 * requerimientos establecidos en los Dto mediante las anotaciones {@link Valid}
	 * @param e
	 * @return {@link ExceptionResponse}
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException err) {
		ExceptionResponse response = new ExceptionResponse();
        response.setCode("BAD_REQUEST");
		List<String> errors = new ArrayList<String>();
	    err.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.add(fieldName+":"+errorMessage);
	    });
	    response.setMessage(errors.toString());
        response.setStatus(400);
        response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * <h2>Descripción:</h2>
	 * Manejo de excepción cuando se detectan fallos en de formato en alguna<br>
	 * de las peticiones get,post,put,delete.
	 * @param e
	 * @return {@link ExceptionResponse}
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ExceptionResponse> handleHttpRequestMethodNotSupporte(Exception err) {
		ExceptionResponse response = new ExceptionResponse();
        response.setCode("BAD_REQUEST");
	    response.setMessage(err.getMessage());
        response.setStatus(400);
        response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
		
	}
	
}
