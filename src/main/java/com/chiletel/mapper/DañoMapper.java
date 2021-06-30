package com.chiletel.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.chiletel.dto.DañoNuevoDTO;
import com.chiletel.dto.DañoVerReporteDTO;
import com.chiletel.dto.TecnicoDTO;
import com.chiletel.entity.Daño;
import com.chiletel.entity.Tecnico;

@Component("dañoMapper")
@Configuration
public class DañoMapper {

	@Autowired
	private ModelMapper mapper;
	
	
	//Mapero de entidad a DañoVerReporteDTO 
	public DañoVerReporteDTO toDtoDañoVerReporte(Daño daño) {
		DañoVerReporteDTO dañoVerReporteDTO=null;
		if(daño!=null) {
			mapper.typeMap(Daño.class,DañoVerReporteDTO.class)
			.addMapping(x -> x.getCliente().getNumeroIden(), DañoVerReporteDTO::setNumeroIden)
			.addMapping(x -> x.getTipoDaño().getNombre(), DañoVerReporteDTO::setTipoDaño);
			dañoVerReporteDTO=mapper.map(daño, DañoVerReporteDTO.class);
		}
		return dañoVerReporteDTO;
	}
	//Mapero de lista entidades a DañoVerReporteDTO 
	public List<DañoVerReporteDTO> toDtoDañoVerReportes(List<Daño> daños){
		return daños.stream()
				  .map(Daño->toDtoDañoVerReporte(Daño))
				  .collect(Collectors.toList());
	}
	
	public Daño toEntity(DañoNuevoDTO dañoNuevoDTO) {
		Daño daño=null;
		if(dañoNuevoDTO!=null) {
			daño=mapper.map(dañoNuevoDTO, Daño.class);
		}
		return daño;
	}
	
	
	
}
