package com.chiletel.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.chiletel.dto.CuadrillaDTO;
import com.chiletel.dto.TipoDa�oDTO;
import com.chiletel.entity.Cuadrilla;
import com.chiletel.entity.TipoDa�o;

@Component("TipoDa�oMapper")
@Configuration
public class TipoDa�oMapper {
	@Autowired
	private ModelMapper mapper;
	
	public TipoDa�oDTO toDto(TipoDa�o tipoDa�o) {
		TipoDa�oDTO tipoDa�oDTO=null;
		if(tipoDa�o!=null) {
			tipoDa�oDTO=mapper.map(tipoDa�o, TipoDa�oDTO.class);
		}
		return tipoDa�oDTO;
	}
	
	public List<TipoDa�oDTO> toDtos(List<TipoDa�o> tipoDa�os){
		return tipoDa�os.stream()
				  .map(tDa�o->toDto(tDa�o))
				  .collect(Collectors.toList());
		
	}
	
	
	public TipoDa�o toEntity(TipoDa�oDTO tipoDa�oDTO) {
		TipoDa�o tipoDa�o=null;
		if(tipoDa�oDTO!=null) {
			tipoDa�o=mapper.map(tipoDa�oDTO, TipoDa�o.class);
		}
		return tipoDa�o;
	}
	
	public List<TipoDa�o> toEntities(List<TipoDa�oDTO> tipoDa�osDTO){
		return tipoDa�osDTO.stream()
				  .map(tDa�oDTO->toEntity(tDa�oDTO))
				  .collect(Collectors.toList());
	}
}
