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

import com.chiletel.dto.DañoNuevoDTO;
import com.chiletel.dto.DañoVerReporteDTO;
import com.chiletel.service.IDañoService;
import com.chiletel.utils.MessageOk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <h2>Descripción:</h2>
 * Controlador en el que exponen los endpoint del api para gestionar los daños.
 * @author Brayan  Baquero
 */
@Api(tags = "5: Daños de servicio",description = "Daños en el servicio de telefonico")
@RestController
@RequestMapping("/api/daño")
@CrossOrigin
public class DañoController {
	
	@Autowired
	private IDañoService iDañoService;
	
	@ApiOperation(value = "Obtiene lista de daños reportados  ")
	@GetMapping
	public ResponseEntity<Page<DañoVerReporteDTO>> getAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10")int size
			){
		Pageable pageable=PageRequest.of(page, size,Sort.by("fechaRegistro").descending());
		return new ResponseEntity<Page<DañoVerReporteDTO>>(iDañoService.getDaños(pageable),HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Registrar un nuevo daño")
	@PostMapping
	public ResponseEntity<MessageOk> add(@Valid @RequestBody DañoNuevoDTO dañoDTO){
		iDañoService.addDaño(dañoDTO);
		return new ResponseEntity<MessageOk>(new MessageOk("Se ha registrado el daño."),HttpStatus.CREATED);
	}
	
	
}
