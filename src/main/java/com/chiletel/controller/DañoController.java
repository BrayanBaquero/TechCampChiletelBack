package com.chiletel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chiletel.dto.Da�oNuevoDTO;
import com.chiletel.dto.Da�oVerReporteDTO;
import com.chiletel.service.IDa�oService;
import com.chiletel.utils.MessageOk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "5: Da�os de servicio",description = "Da�os en el servicio de telefonico")
@RestController
@RequestMapping("/api/da�o")
@CrossOrigin
public class Da�oController {
	
	@Autowired
	private IDa�oService iDa�oService;
	
	@ApiOperation(value = "Obtiene lista de da�os reportados  ")
	@GetMapping("/page")
	public ResponseEntity<List<Da�oVerReporteDTO>> getAll(){
		return new ResponseEntity<List<Da�oVerReporteDTO>>(iDa�oService.getAllDa�os(),HttpStatus.OK);
	}
	
	@ApiOperation(value = "Registrar un nuevo da�o")
	@PostMapping
	public ResponseEntity<?> add(@RequestBody Da�oNuevoDTO da�oDTO){
		iDa�oService.addDa�o(da�oDTO);
		return new ResponseEntity<>(new MessageOk("Se ha registrado el da�o."),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<Page<Da�oVerReporteDTO>> getPage(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10")int size){
		Pageable pageable=PageRequest.of(page, size,Sort.by("fechaRegistro").descending());
		return new ResponseEntity<Page<Da�oVerReporteDTO>>(iDa�oService.getPagenDa�os(pageable),HttpStatus.OK);
	}
}
