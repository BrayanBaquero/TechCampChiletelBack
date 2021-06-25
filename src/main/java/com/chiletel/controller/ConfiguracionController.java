package com.chiletel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiletel.dto.TipoClienteDTO;
import com.chiletel.dto.TipoDa�oDTO;
import com.chiletel.service.ITipoClienteService;
import com.chiletel.service.ITipoDa�oService;
import com.chiletel.utils.MessageOk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "4: Configuraci�n",description = "Configuraci�n de tipos de cliente y tipos de da�os")
@RestController
@RequestMapping("/api/configuracion")
@CrossOrigin
public class ConfiguracionController {
	
	@Autowired
	private ITipoDa�oService iTipoDa�oService;
	
	@Autowired
	private ITipoClienteService iTipoClienteService;
	
	
	
	@ApiOperation(value = "Obtener lista de tipos de clientes")
	@GetMapping("/tipocliente")
	private ResponseEntity<List<TipoClienteDTO>> getAllTipoCliente(){
		return new ResponseEntity(iTipoClienteService.getAllTipoCliente(),HttpStatus.ACCEPTED);
	}
	
	@ApiOperation(value = "Actualizar prioridad de tipo de cliente")
	@PutMapping("/tipocliente")
	private ResponseEntity<?> updateTipoCliente(@RequestBody List<TipoClienteDTO> clienteDTOs){
		iTipoClienteService.Update(clienteDTOs);
		return new ResponseEntity(new MessageOk("Se actualizaron los datos correctamente"),HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Obtener lista de tipos de da�o")
	@GetMapping("/tipodano")
	public ResponseEntity<List<TipoDa�oDTO>> getAllTipoDa�o(){
		return new ResponseEntity(iTipoDa�oService.getAllTipoDa�o(),HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualizar tiempo de atenci�n de los tipos de da�o")
	@PutMapping("/tipodano")
	public ResponseEntity<?> updateTipoDa�o(@RequestBody  List<TipoDa�oDTO> tipoDa�oDTOs){
		iTipoDa�oService.updateTipoDa�o(tipoDa�oDTOs);
		
		return new ResponseEntity(new MessageOk("Se actualizaron los datos correctamente"),HttpStatus.OK);
	}

}
