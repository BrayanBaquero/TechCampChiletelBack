package com.chiletel.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.chiletel.dto.TipoDanoDTO;
import com.chiletel.entity.TipoDano;

/**
 * <h2>Descripción:</h2>
 * Clase encarga de realizar mapeo de datos entre la entidad {@link TipoDano} y el dto {@link TipoDanoDTO} 
 * usando la libreria {@link ModelMapper}
 * @author Brayan Baquero
 */
@Component("TipoDañoMapper")
@Configuration
public class TipoDanoMapper {
	@Autowired
	private ModelMapper mapper;
	
	/**
	 * <h2>Descripción:</h2>
	 * Metodo encargado de mapear {@link TipoDano} a {@link TipoDanoDTO}
	 * @param {@link {@link TipoDano}}
	 * @return {@link TipoDanoDTO}
	 */
	public TipoDanoDTO toDto(TipoDano tipoDano) {
		TipoDanoDTO tipoDanoDTO=null;
		if(tipoDano!=null) {
			tipoDanoDTO=mapper.map(tipoDano, TipoDanoDTO.class);
		}
		return tipoDanoDTO;
	}
	
	/**
	 * <h2>Descripción:</h2>
	 * Metodo que convierte de List<{@link TipoDano}> a List<{@link TipoDanoDTO}>
	 * @param tipoDaños
	 * @return List<{@link TipoDanoDTO}>
	 */
	public List<TipoDanoDTO> toDtos(List<TipoDano> tipoDanos){
		return tipoDanos.stream().map(tDano->toDto(tDano)).collect(Collectors.toList());
	}
	
	/**
	 * <h2>Descripción:</h2>
	 * Metodo encargado de mapear {@link TipoDanoDTO} a {@link TipoDano}
	 * @param {@link {@link TipoDanoDTO}}
	 * @return {@link TipoDano}
	 */
	public TipoDano toEntity(TipoDanoDTO tipoDanoDTO) {
		TipoDano tipoDano=null;
		if(tipoDanoDTO!=null) {
			tipoDano=mapper.map(tipoDanoDTO, TipoDano.class);
		}
		return tipoDano;
	}
	/**
	 * <h2>Descripción:</h2>
	 * Metodo que convierte de List<{@link TipoDanoDTO}> a List<{@link TipoDano}>
	 * @param tipoDañosDTO
	 * @return List<{@link TipoDano}>
	 */
	public List<TipoDano> toEntities(List<TipoDanoDTO> tipoDanosDTO){
		return tipoDanosDTO.stream()
				  .map(tDanoDTO->toEntity(tDanoDTO))
				  .collect(Collectors.toList());
	}
}
