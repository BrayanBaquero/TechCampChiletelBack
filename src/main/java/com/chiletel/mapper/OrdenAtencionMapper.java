package com.chiletel.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.chiletel.dto.OrdenAtencionDTO;
import com.chiletel.dto.TecnicoDTO;
import com.chiletel.entity.OrdenAtencion;
import com.chiletel.entity.Tecnico;

@Component("mapperOrdenAtencion")
@Configuration
public class OrdenAtencionMapper {
	@Autowired
	private ModelMapper mapper;
	
	
	public OrdenAtencionDTO toDTO(OrdenAtencion ordenAtencion) {
		OrdenAtencionDTO ordenAtencionDTO=null;
		if(ordenAtencion!=null) {
			mapper.typeMap(OrdenAtencion.class, OrdenAtencionDTO.class)
			.addMapping(x -> x.getDaño().getTipoDaño().getNombre(), OrdenAtencionDTO::setIncidencia)
			.addMapping(x -> x.getDaño().getCliente().getNumeroIden(), OrdenAtencionDTO::setCliente);
			ordenAtencionDTO=mapper.map(ordenAtencion, OrdenAtencionDTO.class);
		}
		return ordenAtencionDTO;
	}
	
	public List<OrdenAtencionDTO> toDTOs(List<OrdenAtencion> ordenesAtencion){
		return ordenesAtencion.stream()
				  .map(orden->toDTO(orden))
				  .collect(Collectors.toList());
	}
}
