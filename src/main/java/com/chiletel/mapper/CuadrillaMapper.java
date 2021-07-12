package com.chiletel.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.chiletel.dto.CuadrillaDTO;
import com.chiletel.entity.Cuadrilla;
import com.chiletel.entity.Zona;
/**
 * <h2>Descripción:</h2>
 * Clase encarga de realizar mapeo de datos entre la entidad {@link Cuadrilla} y el dto {@link CuadrillaDTO} 
 * usando la libreria {@link ModelMapper}
 * @author Brayan Baquero
 *
 */
@Component("cuadrillaMapper")
@Configuration
public class CuadrillaMapper {
	@Autowired
	private ModelMapper mapper;

	/**
	<h2>Descripción:</h2>
	Conversor de la libreria {@link ModelMapper} para obtener de un 
	Set<{@link Zona}> un  Set< {@link String}
	 */
	Converter<Set<Zona>, Set<String>> ZonasListConverter = new AbstractConverter<Set<Zona>, Set<String>>() {
		@Override
		protected Set<String> convert(Set<Zona> source) {
			return source.stream().map(Zona::getNombre).collect(Collectors.toSet());
		}
	};
	/**
	 * <h2>Descripción:</h2>
	 * Metodo encargado de mapear {@link Cuadrilla} a {@link CuadrillaDTO}
	 * @param {@link {@link Cuadrilla}}
	 * @return {@link CuadrillaDTO}
	 */
	public CuadrillaDTO toDto(Cuadrilla cuadrilla) {
		CuadrillaDTO cuadrillaDTO=null;
		if(cuadrilla!=null) {
			mapper.typeMap(Cuadrilla.class, CuadrillaDTO.class)
			.addMappings(mapper->mapper.using(ZonasListConverter).map(Cuadrilla::getZona, CuadrillaDTO::setZona));
		cuadrillaDTO=mapper.map(cuadrilla, CuadrillaDTO.class);
		}
		return cuadrillaDTO;
	}
	
	/**
	 * <h2>Descripción:</h2>
	 * Metodo que convierte de List<{@link Cuadrilla}> a List<{@link CuadrillaDTO}>
	 * @param cuadrillas
	 * @return List<{@link CuadrillaDTO}>
	 */
	public List<CuadrillaDTO> toDtos(List<Cuadrilla> cuadrillas){
		return cuadrillas.stream().map(cuadrilla->toDto(cuadrilla)).collect(Collectors.toList());
	}

}
