package com.chiletel.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.chiletel.dto.TipoDañoDTO;
import com.chiletel.entity.TipoDaño;

/**
 * <h2>Descripción:</h2>
 * Clase encarga de realizar mapeo de datos entre la entidad {@link TipoDaño} y el dto {@link TipoDañoDTO} 
 * usando la libreria {@link ModelMapper}
 * @author Brayan Baquero
 */
@Component("TipoDañoMapper")
@Configuration
public class TipoDañoMapper {
	@Autowired
	private ModelMapper mapper;
	
	/**
	 * <h2>Descripción:</h2>
	 * Metodo encargado de mapear {@link TipoDaño} a {@link TipoDañoDTO}
	 * @param {@link {@link TipoDaño}}
	 * @return {@link TipoDañoDTO}
	 */
	public TipoDañoDTO toDto(TipoDaño tipoDaño) {
		TipoDañoDTO tipoDañoDTO=null;
		if(tipoDaño!=null) {
			tipoDañoDTO=mapper.map(tipoDaño, TipoDañoDTO.class);
		}
		return tipoDañoDTO;
	}
	
	/**
	 * <h2>Descripción:</h2>
	 * Metodo que convierte de List<{@link TipoDaño}> a List<{@link TipoDañoDTO}>
	 * @param tipoDaños
	 * @return List<{@link TipoDañoDTO}>
	 */
	public List<TipoDañoDTO> toDtos(List<TipoDaño> tipoDaños){
		return tipoDaños.stream().map(tDaño->toDto(tDaño)).collect(Collectors.toList());
	}
	
	/**
	 * <h2>Descripción:</h2>
	 * Metodo encargado de mapear {@link TipoDañoDTO} a {@link TipoDaño}
	 * @param {@link {@link TipoDañoDTO}}
	 * @return {@link TipoDaño}
	 */
	public TipoDaño toEntity(TipoDañoDTO tipoDañoDTO) {
		TipoDaño tipoDaño=null;
		if(tipoDañoDTO!=null) {
			tipoDaño=mapper.map(tipoDañoDTO, TipoDaño.class);
		}
		return tipoDaño;
	}
	/**
	 * <h2>Descripción:</h2>
	 * Metodo que convierte de List<{@link TipoDañoDTO}> a List<{@link TipoDaño}>
	 * @param tipoDañosDTO
	 * @return List<{@link TipoDaño}>
	 */
	public List<TipoDaño> toEntities(List<TipoDañoDTO> tipoDañosDTO){
		return tipoDañosDTO.stream()
				  .map(tDañoDTO->toEntity(tDañoDTO))
				  .collect(Collectors.toList());
	}
}
