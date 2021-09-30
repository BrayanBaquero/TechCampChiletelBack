package com.chiletel.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiletel.dto.TipoClienteDTO;
import com.chiletel.dto.TipoDanoDTO;
import com.chiletel.service.ITipoClienteService;
import com.chiletel.service.ITipoDanoService;
import com.chiletel.utils.MessageOk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <h2>Descripción:</h2>
 * Controlador en el que se exponen los endpoint del api para gestionar la configuración<br>
 * de tipos de daño y tipos de cliente
 * @author Brayan  Baquero
 */

@Api(tags = "4: Configuración",description = "Configuración de tipos de cliente y tipos de daños")
@RestController
@RequestMapping("/api/configuracion")
@CrossOrigin
public class ConfiguracionController {
	
	@Autowired
	private ITipoDanoService iTipoDanoService;
	@Autowired
	private ITipoClienteService iTipoClienteService;
	
	@ApiOperation(value = "Obtener lista de tipos de clientes")
	@GetMapping("/tipocliente")
	private ResponseEntity<List<TipoClienteDTO>> getAllTipoCliente(){
		return new ResponseEntity<List<TipoClienteDTO>>(iTipoClienteService.getAllTipoCliente(),HttpStatus.ACCEPTED);
	}
	
	@ApiOperation(value = "Actualizar prioridad de tipo de cliente")
	@PutMapping("/tipocliente")
	private ResponseEntity<MessageOk> updateTipoCliente(@Valid @RequestBody List<TipoClienteDTO> clienteDTOs){
		iTipoClienteService.updateTipoCliente(clienteDTOs);
		return new ResponseEntity<MessageOk>(new MessageOk("Se actualizaron los datos correctamente."),HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Obtener lista de tipos de daño")
	@GetMapping("/tipodano")
	public ResponseEntity<List<TipoDanoDTO>> getAllTipoDano(){
		return new ResponseEntity<List<TipoDanoDTO>>(iTipoDanoService.getAllTipoDano(),HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualizar tiempo de atención de los tipos de daño")
	@PutMapping("/tipodano")
	public ResponseEntity<MessageOk> updateTipoDano(@Valid @RequestBody  List<TipoDanoDTO> tipoDanoDTOs){
		iTipoDanoService.updateTipoDano(tipoDanoDTOs);
		return new ResponseEntity<MessageOk>(new MessageOk("Se actualizaron los datos correctamente."),HttpStatus.OK);
	}

}
