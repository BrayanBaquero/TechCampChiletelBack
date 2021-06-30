package com.chiletel.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.chiletel.dto.Da�oNuevoDTO;
import com.chiletel.dto.Da�oVerReporteDTO;
import com.chiletel.dto.TecnicoDTO;
import com.chiletel.entity.Da�o;
import com.chiletel.entity.Tecnico;

@Component("da�oMapper")
@Configuration
public class Da�oMapper {

	@Autowired
	private ModelMapper mapper;
	
	
	//Mapero de entidad a Da�oVerReporteDTO 
	public Da�oVerReporteDTO toDtoDa�oVerReporte(Da�o da�o) {
		Da�oVerReporteDTO da�oVerReporteDTO=null;
		if(da�o!=null) {
			mapper.typeMap(Da�o.class,Da�oVerReporteDTO.class)
			.addMapping(x -> x.getCliente().getNumeroIden(), Da�oVerReporteDTO::setNumeroIden)
			.addMapping(x -> x.getTipoDa�o().getNombre(), Da�oVerReporteDTO::setTipoDa�o);
			da�oVerReporteDTO=mapper.map(da�o, Da�oVerReporteDTO.class);
		}
		return da�oVerReporteDTO;
	}
	//Mapero de lista entidades a Da�oVerReporteDTO 
	public List<Da�oVerReporteDTO> toDtoDa�oVerReportes(List<Da�o> da�os){
		return da�os.stream()
				  .map(Da�o->toDtoDa�oVerReporte(Da�o))
				  .collect(Collectors.toList());
	}
	
	public Da�o toEntity(Da�oNuevoDTO da�oNuevoDTO) {
		Da�o da�o=null;
		if(da�oNuevoDTO!=null) {
			da�o=mapper.map(da�oNuevoDTO, Da�o.class);
		}
		return da�o;
	}
	
	
	
}
