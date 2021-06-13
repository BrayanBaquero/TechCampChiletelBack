package com.chiletel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.chiletel.entity.Cliente;
import com.chiletel.entity.TipoCliente;
import com.chiletel.mapper.MapperData;
import com.chiletel.repository.IClienteRepository;
import com.chiletel.repository.ITipoCliente;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cliente")
public class ClienteController {
	
	@Autowired
	public IClienteRepository clienteRepo;
	
	@Autowired
	public MapperData mapp;
	
	@Autowired 
	public ITipoCliente tipoCliente;
	
	//@PostMapping("/agregar")
	//public ResponseEntity<ClienteDTO> agregar(@RequestBody Cliente cliente){
	//	return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapp.clienteToDto(clienteRepo.save(cliente)));
	//}
	//@ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
	
	
	@GetMapping("/todos")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<TipoCliente>> obtener(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(tipoCliente.findAll());
	}

}
