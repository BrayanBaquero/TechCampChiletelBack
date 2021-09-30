package com.chiletel.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.chiletel.dto.DanoNuevoDTO;
import com.chiletel.dto.DanoVerReporteDTO;
import com.chiletel.entity.Dano;

/**
 * <h2>Descripción:</h2>
 * Clase encarga de realizar mapeo de datos entre la entidad {@link Dano} y el dto {@link DanoVerReporteDTO} <br>
 * y {@link DanoNuevoDTO}
 * usando la libreria {@link ModelMapper}
 * @author Brayan Baquero
 */
@Component("dañoMapper")
@Configuration
public class DanoMapper {

	@Autowired
	private ModelMapper mapper;
	
	/**
	 * <h2>Descripción:</h2>
	 *  Metodo encargado de mapear {@link Dano} a {@link DanoVerReporteDTO}
	 * @param dano
	 * @return {@link DanoVerReporteDTO}
	 */
	public DanoVerReporteDTO toDtoDanoVerReporte(Dano dano) {
		DanoVerReporteDTO danoVerReporteDTO=null;
		if(dano!=null) {
			mapper.typeMap(Dano.class,DanoVerReporteDTO.class)
			.addMapping(x -> x.getCliente().getNumeroIden(), DanoVerReporteDTO::setNumeroIden)
			.addMapping(x -> x.getTipoDano().getNombre(), DanoVerReporteDTO::setTipoDano);
			danoVerReporteDTO=mapper.map(dano, DanoVerReporteDTO.class);
		}
		return danoVerReporteDTO;
	}
	/**
	 * <h2>Descripción:</h2>
	 *  Metodo encargado de mapear List<{@link Dano}> a List<{@link DanoVerReporteDTO}>
	 * @param List<Daño>
	 * @return List<{@link DanoVerReporteDTO}>
	 */
	//Mapero de lista entidades a DañoVerReporteDTO 
	public List<DanoVerReporteDTO> toDtoDanoVerReportes(List<Dano> danos){
		return danos.stream().map(Dano->toDtoDanoVerReporte(Dano)).collect(Collectors.toList());
	}
	
	/**
	 * <h2>Descripción:</h2>
	 *  Metodo encargado de mapear {@link DanoNuevoDTO} a {@link Dano}
	 * @param DanoNuevoDTO
	 * @return {@link Dano}
	 */
	public Dano toEntity(DanoNuevoDTO danoNuevoDTO) {
		Dano dano=null;
		if(danoNuevoDTO!=null) {
			dano=mapper.map(danoNuevoDTO, Dano.class);
		}
		return dano;
	}
	
}
