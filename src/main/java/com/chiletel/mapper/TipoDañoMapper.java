package com.chiletel.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.chiletel.dto.TipoDa�oDTO;
import com.chiletel.entity.TipoDa�o;

/**
 * <h2>Descripci�n:</h2>
 * Clase encarga de realizar mapeo de datos entre la entidad {@link TipoDa�o} y el dto {@link TipoDa�oDTO} 
 * usando la libreria {@link ModelMapper}
 * @author Brayan Baquero
 */
@Component("TipoDa�oMapper")
@Configuration
public class TipoDa�oMapper {
	@Autowired
	private ModelMapper mapper;
	
	/**
	 * <h2>Descripci�n:</h2>
	 * Metodo encargado de mapear {@link TipoDa�o} a {@link TipoDa�oDTO}
	 * @param {@link {@link TipoDa�o}}
	 * @return {@link TipoDa�oDTO}
	 */
	public TipoDa�oDTO toDto(TipoDa�o tipoDa�o) {
		TipoDa�oDTO tipoDa�oDTO=null;
		if(tipoDa�o!=null) {
			tipoDa�oDTO=mapper.map(tipoDa�o, TipoDa�oDTO.class);
		}
		return tipoDa�oDTO;
	}
	
	/**
	 * <h2>Descripci�n:</h2>
	 * Metodo que convierte de List<{@link TipoDa�o}> a List<{@link TipoDa�oDTO}>
	 * @param tipoDa�os
	 * @return List<{@link TipoDa�oDTO}>
	 */
	public List<TipoDa�oDTO> toDtos(List<TipoDa�o> tipoDa�os){
		return tipoDa�os.stream().map(tDa�o->toDto(tDa�o)).collect(Collectors.toList());
	}
	
	/**
	 * <h2>Descripci�n:</h2>
	 * Metodo encargado de mapear {@link TipoDa�oDTO} a {@link TipoDa�o}
	 * @param {@link {@link TipoDa�oDTO}}
	 * @return {@link TipoDa�o}
	 */
	public TipoDa�o toEntity(TipoDa�oDTO tipoDa�oDTO) {
		TipoDa�o tipoDa�o=null;
		if(tipoDa�oDTO!=null) {
			tipoDa�o=mapper.map(tipoDa�oDTO, TipoDa�o.class);
		}
		return tipoDa�o;
	}
	/**
	 * <h2>Descripci�n:</h2>
	 * Metodo que convierte de List<{@link TipoDa�oDTO}> a List<{@link TipoDa�o}>
	 * @param tipoDa�osDTO
	 * @return List<{@link TipoDa�o}>
	 */
	public List<TipoDa�o> toEntities(List<TipoDa�oDTO> tipoDa�osDTO){
		return tipoDa�osDTO.stream()
				  .map(tDa�oDTO->toEntity(tDa�oDTO))
				  .collect(Collectors.toList());
	}
}
