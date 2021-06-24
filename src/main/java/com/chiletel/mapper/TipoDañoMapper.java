package com.chiletel.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.chiletel.dto.CuadrillaDTO;
import com.chiletel.dto.TipoDañoDTO;
import com.chiletel.entity.Cuadrilla;
import com.chiletel.entity.TipoDaño;

@Component("TipoDañoMapper")
@Configuration
public class TipoDañoMapper {
	@Autowired
	private ModelMapper mapper;
	
	public TipoDañoDTO toDto(TipoDaño tipoDaño) {
		TipoDañoDTO tipoDañoDTO=null;
		if(tipoDaño!=null) {
			tipoDañoDTO=mapper.map(tipoDaño, TipoDañoDTO.class);
		}
		return tipoDañoDTO;
	}
	
	public List<TipoDañoDTO> toDtos(List<TipoDaño> tipoDaños){
		return tipoDaños.stream()
				  .map(tDaño->toDto(tDaño))
				  .collect(Collectors.toList());
		
	}
	
	
	public TipoDaño toEntity(TipoDañoDTO tipoDañoDTO) {
		TipoDaño tipoDaño=null;
		if(tipoDañoDTO!=null) {
			tipoDaño=mapper.map(tipoDañoDTO, TipoDaño.class);
		}
		return tipoDaño;
	}
	
	public List<TipoDaño> toEntities(List<TipoDañoDTO> tipoDañosDTO){
		return tipoDañosDTO.stream()
				  .map(tDañoDTO->toEntity(tDañoDTO))
				  .collect(Collectors.toList());
	}
}
