package com.chiletel.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.chiletel.dto.OrdenAtencionDTO;
import com.chiletel.entity.OrdenAtencion;

/**
 * <h2>Descripción:</h2>
 * Clase encarga de realizar mapeo de datos entre la entidad {@link OrdenAtencion} y el dto {@link OrdenAtencionDTO} 
 * usando la libreria {@link ModelMapper}
 * @author Brayan Baquero
 *
 */
@Component("mapperOrdenAtencion")
@Configuration
public class OrdenAtencionMapper {
	@Autowired
	private ModelMapper mapper;
	
	/**
	 * <h2>Descripción:</h2>
	 * Metodo encargado de mapear {@link OrdenAtencion} a {@link OrdenAtencionDTO}
	 * @param ordenAtencion
	 * @return {@link OrdenAtencionDTO}
	 */
	public OrdenAtencionDTO toDTO(OrdenAtencion ordenAtencion) {
		OrdenAtencionDTO ordenAtencionDTO=null;
		if(ordenAtencion!=null) {
			mapper.typeMap(OrdenAtencion.class, OrdenAtencionDTO.class)
			.addMapping(x -> x.getDano().getTipoDano().getNombre(), OrdenAtencionDTO::setIncidencia)
			.addMapping(x -> x.getDano().getCliente().getNumeroIden(), OrdenAtencionDTO::setCliente)
			.addMapping(x -> x.getId(), OrdenAtencionDTO::setNumOrden);
			ordenAtencionDTO=mapper.map(ordenAtencion, OrdenAtencionDTO.class);
		}
		return ordenAtencionDTO;
	}
	
	/**
	 * <h2>Descripción:</h2>
	 * Metodo que convierte de List<{@link OrdenAtencion}> a List<{@link OrdenAtencionDTO}>
	 * @param ordenesAtencion
	 * @return List<{@link OrdenAtencionDTO}>
	 */
	public List<OrdenAtencionDTO> toDTOs(List<OrdenAtencion> ordenesAtencion){
		return ordenesAtencion.stream()
				  .map(orden->toDTO(orden))
				  .collect(Collectors.toList());
	}
}
