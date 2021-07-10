package com.chiletel.exceptionHandler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.chiletel.controller.TecnicoController;

import oracle.jdbc.OracleDatabaseException;

//Clase encargada de controllar excepciones de forma global.
@ControllerAdvice
public class ExceptionConfig {
	//Excepción personalizada para NotFound
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ExceptionResponse> notFoundException(Exception e){
		ExceptionResponse response = new ExceptionResponse();
        response.setCode("NOT_FOUND");
        response.setMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.NOT_FOUND);
	}
	
	//Excepción personalizada para BadRequest
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
	
	//Manejo de excepcion cuando se el valor de las fechas mandadas en un requestParam es null
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ExceptionResponse> requestParamPresent(Exception err){
		ExceptionResponse response = new ExceptionResponse();
        response.setCode("BAD_REQUEST");
        response.setMessage(err.getMessage());
        response.setStatus(400);
        response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	//Manejo de excepcion cuando se insertan fechas con mal formato en un RequestParam
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ExceptionResponse> invalidFormatDate(Exception err){
		ExceptionResponse response = new ExceptionResponse();
        response.setCode("BAD_REQUEST");
        response.setMessage(err.getMessage());
        response.setStatus(400);
        response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
	}
}
