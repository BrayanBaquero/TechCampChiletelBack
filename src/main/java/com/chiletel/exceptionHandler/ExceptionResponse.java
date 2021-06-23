package com.chiletel.exceptionHandler;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//Clase que se encarga de construir el Json de respuesta ante las excepciones.
public class ExceptionResponse {
	private String message;
    private String code;
    private int status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;



}
