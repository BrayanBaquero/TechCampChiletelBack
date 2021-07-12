package com.chiletel.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.chiletel.dto.TipoClienteDTO;
import com.chiletel.entity.TipoCliente;

/**
 * <h2>Descripción:</h2>
 * Clase encarga de realizar mapeo de datos entre la entidad {@link TipoCliente} y el dto {@link TipoClienteDTO} 
 * usando la libreria {@link ModelMapper}
 * @author Brayan Baquero
 */
@Component("TipoClienteMapper")
@Configuration
public class TipoClienteMapper {
	@Autowired
	private ModelMapper mapper;
	
	/**
	 * <h2>Descripción:</h2>
	 * Metodo encargado de mapear {@link TipoCliente} a {@link TipoClienteDTO}}
	 * @param {@link {@link TipoCliente}}
	 * @return {@link TipoClienteDTO}
	 */
	public TipoClienteDTO toDto(TipoCliente tipoCliente) {
		TipoClienteDTO tipoClienteDTO=null;
		if(tipoCliente!=null) {
			tipoClienteDTO=mapper.map(tipoCliente, TipoClienteDTO.class);
		}
		return tipoClienteDTO;
	}
	
	/**
	 * <h2>Descripción:</h2>
	 * Metodo que convierte de List<{@link TipoCliente}> a List<{@link TipoClienteDTO}>
	 * @param tipoClientes
	 * @return List<{@link TipoClienteDTO}>
	 */
	public List<TipoClienteDTO> toDtos(List<TipoCliente> tipoClientes){
		return tipoClientes.stream().map(tCliente->toDto(tCliente)).collect(Collectors.toList());
	}

}
