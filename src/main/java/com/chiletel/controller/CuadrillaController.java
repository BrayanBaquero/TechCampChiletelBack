package com.chiletel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiletel.dto.CuadrillaDTO;
import com.chiletel.dto.NuevaCuadrillaDTO;
import com.chiletel.service.ICuadrillaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "3: Cuadrilla",description = "Gestión de cuadrillas")
@RestController
@RequestMapping("/api/cuadrilla")
public class CuadrillaController {
	@Autowired
	private ICuadrillaService iCuadrillaService;
	
	
	
	@ApiOperation(value = "Obtiene todas las cuadrillas creadas y la cantidad de miembros asignados a cada una")
	@GetMapping
	public ResponseEntity<List<CuadrillaDTO>> getAll(){
		return new ResponseEntity(iCuadrillaService.getAll(),HttpStatus.OK);
	}
	
	
	
	@ApiOperation(value = "Se crea una nueva cuadrilla")
	@ApiResponses(value = {
			 @ApiResponse(code=400,message = "Alias de cuadrilla ya existente"),
			 @ApiResponse(code=201,message = "Cuadrilla creada con exito.")
	 })
	@PostMapping
	public  ResponseEntity<?> post(@RequestBody NuevaCuadrillaDTO nuevaCuadrillaDTO) {
		iCuadrillaService.add(nuevaCuadrillaDTO);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	
	@ApiResponses(value = {
			 @ApiResponse(code=400,message = "Numero de identificación ya existente")
	 })
	@ApiOperation(value = "Actualizar datos de cuadrilla")
	@PutMapping("{id}")
	public ResponseEntity<?> update(@RequestBody NuevaCuadrillaDTO nuevaCuadrillaDTO,@PathVariable("id")int id){
		iCuadrillaService.update(id, nuevaCuadrillaDTO);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Eliminar cuadrilla")
	@DeleteMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id")int id){
		iCuadrillaService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
	
}
