package com.chiletel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiletel.dto.TipoClienteDTO;
import com.chiletel.entity.TipoCliente;
import com.chiletel.repository.ITipoCliente;
import com.chiletel.service.ITipoClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "4: Tipo de cliente",description = "Configuración de tipos de cliente")
@RestController
@RequestMapping("/api/tipocliente")
public class TipoClienteController {
	@Autowired
	private ITipoClienteService iTipoClienteService;
	
	@ApiOperation(value = "Obtener lista de tipos de clientes")
	@GetMapping
	private ResponseEntity<List<TipoClienteDTO>> getAll(){
		return new ResponseEntity(iTipoClienteService.getAllTecnicos(),HttpStatus.ACCEPTED);
	}
	
	@ApiOperation(value = "Actualizar prioridad de tipo de cliente")
	@PostMapping
	private ResponseEntity<?> update(@RequestBody List<TipoClienteDTO> clienteDTOs){
		iTipoClienteService.Update(clienteDTOs);
		return new ResponseEntity(HttpStatus.OK);
	}
}
