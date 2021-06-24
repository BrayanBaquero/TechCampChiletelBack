package com.chiletel.exceptionHandler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.chiletel.controller.TecnicoController;

import oracle.jdbc.OracleDatabaseException;

@ControllerAdvice
public class ExceptionConfig {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ExceptionResponse> notFoundException(Exception e){
		ExceptionResponse response = new ExceptionResponse();
        response.setCode("NOT_FOUND");
        response.setMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.NOT_FOUND);
	}
	
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
	public ResponseEntity<ExceptionResponse> noelement(Exception err){
		ExceptionResponse response = new ExceptionResponse();
        response.setCode("BAD_REQUEST");
        response.setMessage(err.getMessage());
        response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
	}
}
