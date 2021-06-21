package com.chiletel.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiletel.dto.TecnicoDTO;
import com.chiletel.exceptionHandler.BadRequestException;
import com.chiletel.exceptionHandler.ExceptionResponse;
import com.chiletel.service.ITecnicoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "2: Técnico",description = "Getión de miembros del equipo técnico")
@RestController
@RequestMapping("/api/tecnico")
public class TecnicoController {
	@Autowired
	private ITecnicoService iTecnicoService;
	
	@ApiOperation(value = "Obtene lista de tecnicos registrados")
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(iTecnicoService.obtenerTecnicos());
	}
	
	@ApiResponses(value = {
			 @ApiResponse(code=400,message = "Numero de identificacion ya existe/El tecnico debe tener minimo un tipo de daño asociado"),
			 @ApiResponse(code=200,message = "Tecnico creado con exito.")
	 })
	@ApiOperation(value = "Se agrega un nuevo tecnico a la empresa")
	@PostMapping
	public ResponseEntity<?> add(@Valid @RequestBody TecnicoDTO tecnicoDTO, BindingResult bResult){
		if(bResult.hasErrors())
			throw new BadRequestException("Error en campos");
		
		iTecnicoService.AddTecnico(tecnicoDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@ApiResponses(value = {
			 @ApiResponse(code=400,message = "El técnico debe tener minimo un tipo de daño asociado")
	 })
	@ApiOperation(value = "Se actualizan los datos personales de un técnico")
	@PutMapping("{identificacion}")
	public ResponseEntity<?> update(@Valid @PathVariable("identificacion")int iden, @RequestBody TecnicoDTO tecnicoDTO, BindingResult bResult){
		if(bResult.hasErrors())
			throw new BadRequestException("Error en validacion de campos");
		iTecnicoService.UpdateTecnico(iden,tecnicoDTO);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@ApiOperation(value = "Se borra un técnico registrado en la empresa")
	@DeleteMapping("{identificacion}")
	public ResponseEntity<?> delete(@PathVariable("identificacion")int identificacion){
		iTecnicoService.DeleteTecnico(identificacion);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
	
	
	@ApiOperation(value = "Se obtienen los datos de un técnico por su identificacion")
	@GetMapping("{identificacion}")
	public ResponseEntity<TecnicoDTO> getById(@PathVariable("identificacion") int idenfificacion){
		TecnicoDTO tecnicoDTO=iTecnicoService.getTecnicoByIdentificacion(idenfificacion);
		return new ResponseEntity<TecnicoDTO>(tecnicoDTO,HttpStatus.OK);
	}
}
