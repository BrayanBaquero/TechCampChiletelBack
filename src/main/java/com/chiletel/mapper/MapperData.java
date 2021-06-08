package com.chiletel.mapper;

import org.mapstruct.Mapper;

import com.chiletel.dto.ClienteDTO;
import com.chiletel.entity.Cliente;

@Mapper(componentModel = "spring")
public interface MapperData {
	
	ClienteDTO clienteToDto(Cliente cliente);
	
	Cliente DtoToCliente (ClienteDTO clienteDTO);

}
