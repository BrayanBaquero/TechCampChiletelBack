package com.chiletel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiletel.dto.TipoDa�oDTO;
import com.chiletel.service.ITipoDa�oService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "5: Tipos de da�o",description = "Configuraci�n de tipos de da�o")
@RestController
@RequestMapping("/api/tipoda�o")
public class TipoDa�oController {
	@Autowired
	private ITipoDa�oService iTipoDa�oService;
	
	@ApiOperation(value = "Obtener todos los tipos de da�o")
	@GetMapping
	public ResponseEntity<List<TipoDa�oDTO>> getAll(){
		return new ResponseEntity(iTipoDa�oService.getAllTipoDa�o(),HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualizar tiempo de atenci�n de los tipos de da�o")
	@PutMapping
	public ResponseEntity<?> update(@RequestBody  List<TipoDa�oDTO> tipoDa�oDTOs){
		iTipoDa�oService.updateTipoDa�o(tipoDa�oDTOs);
		return new ResponseEntity(HttpStatus.OK);
	}
}
