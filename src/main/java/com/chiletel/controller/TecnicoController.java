package com.chiletel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiletel.dto.TecnicoDTO;
import com.chiletel.service.ITecnicoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
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
			 @ApiResponse(code=400,message = "Numero de identificacion ya existe/El tecnico debe tener minimo un tipo de daño asociado")
	 })
	@ApiOperation(value = "Se agrega un nuevo tecnico a la empresa")
	@PostMapping
	public ResponseEntity<?> add(@RequestBody TecnicoDTO tecnicoDTO){
		iTecnicoService.AddTecnico();
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@ApiResponses(value = {
			 @ApiResponse(code=400,message = "El tecnico debe tener minimo un tipo de daño asociado")
	 })
	@ApiOperation(value = "Se actualizan los datos personales de un tecnico")
	@PutMapping("{identificacion}")
	public ResponseEntity<?> update(@PathVariable(required = true,name ="identificacion")String iden, @RequestBody TecnicoDTO tecnicoDTO){
		iTecnicoService.UpdateTecnico(iden);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@ApiOperation(value = "Se borra un tecnico registrado en la empresa")
	@DeleteMapping("{identificacion}")
	public ResponseEntity<?> delete(@PathVariable("identificacion")String identificacion){
		iTecnicoService.DeleteTecnico();
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	@ApiOperation(value = "Se obtienen los datos del un tecnico por su identificacion")
	@GetMapping("{identificacion}")
	public ResponseEntity<TecnicoDTO> getById(@PathVariable("identificacion")String idenfificacion){
		return ResponseEntity.status(HttpStatus.OK).body(iTecnicoService.getTecnicoByIdentificacion(idenfificacion));
	}
}
