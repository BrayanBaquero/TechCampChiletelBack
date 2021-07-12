package com.chiletel.controller;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chiletel.dto.TecnicoDTO;
import com.chiletel.service.ITecnicoService;
import com.chiletel.utils.MessageOk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <h2>Descripción:</h2>
 * Controlador que se exponer endpoint de api para gestionar los técnicos
 * @author Brayan  Baquero
 *
 */
@Api(tags = "2: Técnico",description = "Getión de miembros del equipo técnico")
@RestController
@RequestMapping("/api/tecnico")
@CrossOrigin
public class TecnicoController {
	@Autowired
	private ITecnicoService iTecnicoService;
	
	@ApiOperation(value = "Obtene lista de tecnicos registrados")
	@GetMapping
	public ResponseEntity<Page<TecnicoDTO>> getAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10")int size){
		Pageable pageable=PageRequest.of(page, size);
		return new ResponseEntity<Page<TecnicoDTO>>(iTecnicoService.getTecnicos(pageable),HttpStatus.OK);
	}
	
	@ApiResponses(value = {
			 @ApiResponse(code=400,message = "Numero de identificacion ya existe/El tecnico debe tener minimo un tipo de daño asociado"),
			 @ApiResponse(code=200,message = "Tecnico creado con exito.")
	 })
	@ApiOperation(value = "Se agrega un nuevo tecnico a la empresa")
	@PostMapping
	public ResponseEntity<MessageOk> add(@Valid @RequestBody TecnicoDTO tecnicoDTO){
		iTecnicoService.addTecnico(tecnicoDTO);
		return new ResponseEntity<MessageOk>(new MessageOk("Técnico agregado con exito."),HttpStatus.CREATED);
	}
	
	@ApiResponses(value = {@ApiResponse(code=400,message = "El técnico debe tener minimo un tipo de daño asociado") })
	@ApiOperation(value = "Se actualizan los datos personales de un técnico")
	@PutMapping("{identificacion}")
	public ResponseEntity<MessageOk> update(@Valid @PathVariable("identificacion")Long iden, @Valid @RequestBody TecnicoDTO tecnicoDTO){
		iTecnicoService.updateTecnico(iden,tecnicoDTO);
		return new ResponseEntity<MessageOk>(new MessageOk("Técnico actualizado con exito."),HttpStatus.OK);
	}
	
	@ApiOperation(value = "Se borra un técnico registrado en la empresa")
	@DeleteMapping("{identificacion}")
	public ResponseEntity<MessageOk> delete(@PathVariable("identificacion")Long identificacion){
		iTecnicoService.deleteTecnico(identificacion);
		return new ResponseEntity<MessageOk>(new MessageOk("Técnico borrado con exito."),HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Se obtienen los datos de un técnico por su identificacion")
	@GetMapping("{identificacion}")
	public ResponseEntity<TecnicoDTO> getByIdentificacion(@PathVariable("identificacion") Long idenfificacion){
		TecnicoDTO tecnicoDTO=iTecnicoService.getTecnicoByIdentificacion(idenfificacion);
		return new ResponseEntity<TecnicoDTO>(tecnicoDTO,HttpStatus.OK);
	}
}
