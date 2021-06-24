package com.chiletel.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.chiletel.dto.TipoClienteDTO;
import com.chiletel.entity.TipoCliente;

@Component("TipoClienteMapper")
@Configuration
public class TipoClienteMapper {
	@Autowired
	private ModelMapper mapper;
	
	public TipoClienteDTO toDto(TipoCliente tipoCliente) {
		TipoClienteDTO tipoClienteDTO=null;
		if(tipoCliente!=null) {
			tipoClienteDTO=mapper.map(tipoCliente, TipoClienteDTO.class);
		}
		return tipoClienteDTO;
	}
	
	public List<TipoClienteDTO> toDtos(List<TipoCliente> tipoClientes){
		return tipoClientes.stream()
				  .map(tCliente->toDto(tCliente))
				  .collect(Collectors.toList());
		
	}

}
