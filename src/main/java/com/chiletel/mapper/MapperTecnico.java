package com.chiletel.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.chiletel.dto.TecnicoDTO;
import com.chiletel.entity.Tecnico;
import com.chiletel.entity.TipoDaño;




@Component("mapperTecnico")
@Configuration
public class MapperTecnico  {
	@Autowired
	private ModelMapper mapper;
	
	
	public TecnicoDTO ToDTO (Tecnico tecnico) {
		TecnicoDTO tecnicoDTO=null;
		if(tecnico!=null) {
			mapper.typeMap(Tecnico.class, TecnicoDTO.class)
				.addMapping(x -> x.getCuadrilla().getNombre(), TecnicoDTO::setCuadrilla)
				.addMappings(mapper->mapper.using(new TDañosListConverter()).map(Tecnico::getTDaño, TecnicoDTO::setTDano));
				//.addMappings(m->m.using(ctx->((String)ctx.getSource()).toUpperCase()).map(Tecnico::getNombre, TecnicoDTO::setNombre))
			tecnicoDTO=mapper.map(tecnico, TecnicoDTO.class);
		}
		return tecnicoDTO;
	}

	public Tecnico ToEntity(TecnicoDTO tecnicoDTO) {
		Tecnico tecnico=null;
		if(tecnicoDTO!=null) {
			tecnico=mapper.map(tecnicoDTO, Tecnico.class);
		}
		return tecnico;
	}
	
	public List<TecnicoDTO> ToDTOs (List<Tecnico> tecnicos){
		return tecnicos.stream()
				  .map(tecnico->ToDTO(tecnico))
				  .collect(Collectors.toList());
	}
	
	public Tecnico Update(Tecnico tecnico) {
		Tecnico tecnicoOut=null;
		if(tecnico!=null) {
			tecnicoOut=mapper.map(tecnico, Tecnico.class);
		}
		return tecnicoOut;
	}


}
