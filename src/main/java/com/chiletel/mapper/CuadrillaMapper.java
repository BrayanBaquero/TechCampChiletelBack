package com.chiletel.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.chiletel.dto.CuadrillaDTO;
import com.chiletel.dto.NuevaCuadrillaDTO;
import com.chiletel.entity.Cuadrilla;
import com.chiletel.entity.Zona;

@Component("cuadrillaMapper")
@Configuration
public class CuadrillaMapper {
	

	@Autowired
	private ModelMapper mapper;

	
	
	Converter<Set<String>, Set<Zona>> StringToZona= new AbstractConverter<Set<String>, Set<Zona>>() {

		@Override
		protected Set<Zona> convert(Set<String> source) {
			// TODO Auto-generated method stub
			Set<Zona> zona= source
			          .stream()
			          .map(m->new Zona(0,m))//.forEach(Zona::getNombre);
			          .collect(Collectors.toSet());
			zona.forEach(c->System.out.println(c.getNombre()));
			return zona; 
		}
	}; 
	
	public Cuadrilla toEntity(NuevaCuadrillaDTO nuevacuadrillaDTO) {
		Cuadrilla cuadrilla=null;
		if( nuevacuadrillaDTO!=null) {
			mapper.typeMap(NuevaCuadrillaDTO.class,Cuadrilla.class)
			.addMappings(mapper->mapper.using(StringToZona).map(NuevaCuadrillaDTO::getZona,Cuadrilla::setZona));
			cuadrilla=mapper.map( nuevacuadrillaDTO, Cuadrilla.class);
		}
		return cuadrilla;
	}
	
	public CuadrillaDTO toDto(Cuadrilla cuadrilla) {
		CuadrillaDTO cuadrillaDTO=null;
		if(cuadrilla!=null) {
			mapper.typeMap(Cuadrilla.class, CuadrillaDTO.class)
			.addMappings(mapper->mapper.using(new ZonasListConverter()).map(Cuadrilla::getZona, CuadrillaDTO::setZona));
		cuadrillaDTO=mapper.map(cuadrilla, CuadrillaDTO.class);
		}
		
		return cuadrillaDTO;
	}
	
	public List<CuadrillaDTO> toDtos(List<Cuadrilla> cuadrillas){
		return cuadrillas.stream()
				  .map(cuadrilla->toDto(cuadrilla))
				  .collect(Collectors.toList());
		
	}

}
