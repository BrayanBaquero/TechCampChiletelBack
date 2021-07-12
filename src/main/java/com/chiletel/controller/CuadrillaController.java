package com.chiletel.controller;

import java.util.List;

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

import com.chiletel.dto.CuadrillaDTO;
import com.chiletel.dto.NuevaCuadrillaDTO;
import com.chiletel.service.ICuadrillaService;
import com.chiletel.utils.MessageOk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <h2>Descripción:</h2>
 * Controlador que se exponer endpoint de api para gestionar las cuadrillas.
 * @author Brayan  Baquero
 */
@Api(tags = "3: Cuadrilla",description = "Gestión de cuadrillas")
@RestController
@RequestMapping("/api/cuadrilla")
@CrossOrigin
public class CuadrillaController {
	@Autowired
	private ICuadrillaService iCuadrillaService;
	
	@ApiOperation(value = "Obtiene todas las cuadrillas creadas y la cantidad de miembros asignados a cada una")
	@GetMapping
	public ResponseEntity<Page<CuadrillaDTO>> getAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10")int size
			){
		Pageable pageable=PageRequest.of(page, size);
		return new ResponseEntity<Page<CuadrillaDTO>>(iCuadrillaService.getCuadrillas(pageable),HttpStatus.OK);
	}
	
	@ApiOperation(value = "Se crea una nueva cuadrilla")
	@ApiResponses(value = {
			 @ApiResponse(code=400,message = "Alias de cuadrilla ya existente"),
			 @ApiResponse(code=201,message = "Cuadrilla creada con exito.")
	 })
	@PostMapping
	public  ResponseEntity<MessageOk> add(@Valid @RequestBody NuevaCuadrillaDTO nuevaCuadrillaDTO) {
		iCuadrillaService.addCuadrilla(nuevaCuadrillaDTO);
		return new ResponseEntity<MessageOk>(new MessageOk("Cuadrilla creada con exito."),HttpStatus.CREATED);
	}
	
	
	@ApiResponses(value = {@ApiResponse(code=400,message = "Numero de identificación ya existente")})
	@ApiOperation(value = "Actualizar datos de cuadrilla")
	@PutMapping("{nombre}")
	public ResponseEntity<MessageOk> update(@Valid @RequestBody NuevaCuadrillaDTO nuevaCuadrillaDTO,@PathVariable("nombre")String nombre){
		iCuadrillaService.updateCuadrilla(nombre, nuevaCuadrillaDTO);
		return new ResponseEntity<MessageOk>(new MessageOk("Cuadrilla actualizada con exito."),HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Eliminar cuadrilla")
	@DeleteMapping("{nombre}")
	public ResponseEntity<MessageOk> delete(@PathVariable("nombre")String nombre){
		iCuadrillaService.deleteCuadrilla(nombre);
		return new ResponseEntity<MessageOk>(new MessageOk("Cuadrilla borrada con exito."),HttpStatus.OK);
	}
	
	@ApiOperation(value="Lista de cuadrillas disponibles")
	@GetMapping("/nombres")
	public ResponseEntity<List<String>> getAllNombresCuadrillas(){
		return new ResponseEntity<List<String>>(iCuadrillaService.getAllNombres(),HttpStatus.OK);
	}

}
