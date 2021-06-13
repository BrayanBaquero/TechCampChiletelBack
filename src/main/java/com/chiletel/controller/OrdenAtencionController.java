package com.chiletel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiletel.dto.OrdenAtencionDTO;
import com.chiletel.service.IOrdenAtencionService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/ordenatencion")
public class OrdenAtencionController {
	@Autowired
	private IOrdenAtencionService iOrdenAtencionService;
	
	@ApiOperation(value = "Obtiene Ordenes de atencion no agendadas")
	@GetMapping
	public ResponseEntity<List<OrdenAtencionDTO>> getAll(){
		return new ResponseEntity(iOrdenAtencionService.getAllOrdenesAtencion(),HttpStatus.OK);
	}

}
