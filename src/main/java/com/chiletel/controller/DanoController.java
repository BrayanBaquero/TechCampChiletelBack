package com.chiletel.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chiletel.dto.DanoNuevoDTO;
import com.chiletel.dto.DanoVerReporteDTO;
import com.chiletel.service.IDanoService;
import com.chiletel.utils.MessageOk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <h2>Descripci�n:</h2>
 * Controlador en el que exponen los endpoint del api para gestionar los da�os.
 * @author Brayan  Baquero
 */
@Api(tags = "5: Da�os de servicio",description = "Da�os en el servicio de telefonico")
@RestController
@RequestMapping("/api/da�o")
@CrossOrigin
public class DanoController {
	
	@Autowired
	private IDanoService iDanoService;
	
	@ApiOperation(value = "Obtiene lista de da�os reportados  ")
	@GetMapping
	public ResponseEntity<Page<DanoVerReporteDTO>> getAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10")int size
			){
		Pageable pageable=PageRequest.of(page, size,Sort.by("fechaRegistro").descending());
		return new ResponseEntity<Page<DanoVerReporteDTO>>(iDanoService.getDanos(pageable),HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Registrar un nuevo da�o")
	@PostMapping
	public ResponseEntity<MessageOk> add(@Valid @RequestBody DanoNuevoDTO danoDTO){
		iDanoService.addDano(danoDTO);
		return new ResponseEntity<MessageOk>(new MessageOk("Se ha registrado el da�o."),HttpStatus.CREATED);
	}
	
	
}
