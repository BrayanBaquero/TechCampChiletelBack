package com.chiletel.controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.chiletel.dto.AgendaEventosDTO;
import com.chiletel.dto.AgendaEventosDTOO;
import com.chiletel.dto.AgendaTecnicosDTO;
import com.chiletel.service.IAgendaService;
import com.chiletel.utils.MessageOk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "7: Agenda",description = "Administración de agenda de equipo técnico")
@RestController
@RequestMapping("/api/agenda")
@CrossOrigin
public class AgendaController {
	@Autowired
	private IAgendaService iAgendaService;

	
	
	@ApiOperation(value = "Obtener agenda de trabajo de equipo técnico.")
	@GetMapping
	public ResponseEntity<Page<AgendaTecnicosDTO>> gettAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10")int size
			){
		Pageable pageable=PageRequest.of(page, size);
		return new ResponseEntity<Page<AgendaTecnicosDTO>>(iAgendaService.getAll(pageable),HttpStatus.OK);
	}
	
	@ApiOperation(value = "Generar agenda del equipo tecnico")
	@PostMapping
	public ResponseEntity<?> genAgenda(@RequestBody String msg){
		int code=iAgendaService.genAgenda();
		return new ResponseEntity<>(new MessageOk(""+code) ,HttpStatus.OK);
	}
	
	@ApiOperation(value = "Obtener agenda de trabajo de equipo técnico.")
	@GetMapping("/{ident}/tecnico")
	public ResponseEntity<List<AgendaEventosDTOO>> getAgendaTecnico(
			@PathVariable("ident")Long iden,
			@RequestParam(required = true) @DateTimeFormat(pattern = "dd-MM-yyyy")  Date fechaInicio,
			@RequestParam(required = true) @DateTimeFormat(pattern = "dd-MM-yyyy")  Date fechaFinal
			){
		return new ResponseEntity<List<AgendaEventosDTOO>>(iAgendaService.getAgendaTecnico(iden,fechaInicio,fechaFinal),HttpStatus.OK);
	}
}
