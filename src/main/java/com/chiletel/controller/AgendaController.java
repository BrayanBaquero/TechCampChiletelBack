package com.chiletel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiletel.dto.AgendaDTO;
import com.chiletel.service.IAgendaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/agenda")
public class AgendaController {
	@Autowired
	private IAgendaService iAgendaService;
	
	
	@ApiOperation(value = "Obtener agenda del dia")
	@GetMapping
	public ResponseEntity<AgendaDTO> gettAll(){
		return null;
	}
}
