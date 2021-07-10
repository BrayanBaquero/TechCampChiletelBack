package com.chiletel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chiletel.dto.OrdenAtencionDTO;
import com.chiletel.service.IOrdenAtencionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "6: Ordenes de atención",description = "Ver ordenes de atención")
@RestController
@RequestMapping("/api/ordenatencion")
@CrossOrigin
public class OrdenAtencionController {
	@Autowired
	private IOrdenAtencionService iOrdenAtencionService;
	
	@ApiOperation(value = "Obtiene ordenes de atención no agendadas")
	@GetMapping
	public ResponseEntity<Page<OrdenAtencionDTO>> getAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10")int size){
		Pageable pageable=PageRequest.of(page, size);
		return new ResponseEntity<Page<OrdenAtencionDTO>>(iOrdenAtencionService.getAllOrdenesAtencion(pageable),HttpStatus.OK);
	}

}
