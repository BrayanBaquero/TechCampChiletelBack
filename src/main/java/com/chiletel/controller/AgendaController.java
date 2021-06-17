package com.chiletel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiletel.dto.AgendaDTO;
import com.chiletel.service.IAgendaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "8: Agenda",description = "Administraci�n de agenda de equipo t�cnico")
@RestController
@RequestMapping("/api/agenda")
public class AgendaController {
	@Autowired
	private IAgendaService iAgendaService;
	
	
	@ApiOperation(value = "Obtener agenda de trabajo de equipo t�cnico.")
	@GetMapping
	public ResponseEntity<AgendaDTO> gettAll(){
		return null;
	}
	
	@ApiOperation(value = "Generar agenda del equipo tecnico")
	@PostMapping
	public ResponseEntity<?> genAgenda(){
		return null;
	}
}
