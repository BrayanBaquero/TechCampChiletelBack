package com.chiletel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiletel.dto.TipoDañoDTO;
import com.chiletel.service.ITipoDañoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api/tipodaño")
public class TipoDañoController {
	@Autowired
	private ITipoDañoService iTipoDañoService;
	
	@ApiOperation(value = "Obtener todos los tipos de daño")
	@GetMapping
	public ResponseEntity<List<TipoDañoDTO>> getAll(){
		return new ResponseEntity(iTipoDañoService.getAllTipoDaño(),HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualizar tiempo de atencion de los tipos de daño")
	@PostMapping
	public ResponseEntity<?> update(@RequestBody  List<TipoDañoDTO> tipoDañoDTOs){
		iTipoDañoService.updateTipoDaño(tipoDañoDTOs);
		return new ResponseEntity(HttpStatus.OK);
	}
}
