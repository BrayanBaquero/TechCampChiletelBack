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

import com.chiletel.dto.DañoDTO;
import com.chiletel.service.IDañoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "5: Daños de servicio",description = "Daños en el servicio de telefonico")
@RestController
@RequestMapping("/api/daño")
public class DañoController {
	
	@Autowired
	private IDañoService iDañoService;
	
	@ApiOperation(value = "Obtiene lista de daños reportados")
	@GetMapping
	public ResponseEntity<List<DañoDTO>> getAll(){
		return new ResponseEntity(iDañoService.getAllDaños(),HttpStatus.OK);
	}
	
	@ApiOperation(value = "Registrar un nuevo daño")
	@PostMapping
	public ResponseEntity<?> add(@RequestBody DañoDTO dañoDTO){
		iDañoService.addDaño(dañoDTO);
		return new ResponseEntity(HttpStatus.OK);
	}
}
