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

import com.chiletel.dto.TecnicoDTO;
import com.chiletel.entity.Tecnico;
import com.chiletel.entity.TipoDa�o;

/**
 * <h2>Descripci�n:</h2>
 * Clase encarga de realizar mapeo de datos entre la entidad {@link Tecnico} y el dto {@link TecnicoDTO} 
 * usando la libreria {@link ModelMapper}
 * @author Brayan Baquero
 *
 */
@Component("mapperTecnico")
@Configuration
public class TecnicoMapper  {
	@Autowired
	private ModelMapper mapper;
	
	/**
	<h2>Descripci�n:</h2>
	Conversor de la libreria {@link ModelMapper} para obtener de un 
	Set<{@link TipoDa�o}> un  Set< TipoDa�o.getNombre()>
	 */
	Converter<Set<TipoDa�o>, Set<String>> TDa�osListConverter = new AbstractConverter<Set<TipoDa�o>, Set<String>>() {
		@Override
		protected Set<String> convert(Set<TipoDa�o> source) {
			return source.stream().map(TipoDa�o::getNombre).collect(Collectors.toSet());
			}
		};
	
	/**
	 * <h2>Descripci�n:</h2>
	 * Metodo encargado de mapear {@link Tecnico} a {@link TecnicoDTO}
	 * @param {@link {@link Tecnico}}
	 * @return {@link TecnicoDTO}
	 */
	public TecnicoDTO toDTO (Tecnico tecnico) {
		TecnicoDTO tecnicoDTO=null;
		if(tecnico!=null) {
			mapper.typeMap(Tecnico.class, TecnicoDTO.class)
				.addMapping(x -> x.getCuadrilla().getNombre(), TecnicoDTO::setCuadrilla)
				.addMappings(mapper->mapper.using(TDa�osListConverter).map(Tecnico::getTDa�o, TecnicoDTO::setTDano));
				//.addMappings(m->m.using(ctx->((String)ctx.getSource()).toUpperCase()).map(Tecnico::getNombre, TecnicoDTO::setNombre))
			tecnicoDTO=mapper.map(tecnico, TecnicoDTO.class);
		}
		return tecnicoDTO;
	}
	
	/**
	 * <h2>Descripci�n:</h2>
	 * Metodo que convierte de List<{@link Tecnico}> a List<{@link TecnicoDTO}>
	 * @param tecnicos
	 * @return List<{@link Tecnico}>
	 */
	public List<TecnicoDTO> toDTOs (List<Tecnico> tecnicos){
		return tecnicos.stream().map(tecnico->toDTO(tecnico)).collect(Collectors.toList());
	}
	
	/**
	 * <h2>Descripci�n:</h2>
	 * Convertir de {@link TecnicoDTO} a {@link Tecnico}
	 * @param tecnicoDTO
	 * @return {@link Tecnico}
	 */
	public Tecnico ToEntity(TecnicoDTO tecnicoDTO) {
		Tecnico tecnico=null;
		if(tecnicoDTO!=null) {
			tecnico=mapper.map(tecnicoDTO, Tecnico.class);
		}
		return tecnico;
	}
	
}
