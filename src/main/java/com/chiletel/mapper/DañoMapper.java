package com.chiletel.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.chiletel.dto.DañoNuevoDTO;
import com.chiletel.dto.DañoVerReporteDTO;
import com.chiletel.entity.Daño;

/**
 * <h2>Descripción:</h2>
 * Clase encarga de realizar mapeo de datos entre la entidad {@link Daño} y el dto {@link DañoVerReporteDTO} <br>
 * y {@link DañoNuevoDTO}
 * usando la libreria {@link ModelMapper}
 * @author Brayan Baquero
 */
@Component("dañoMapper")
@Configuration
public class DañoMapper {

	@Autowired
	private ModelMapper mapper;
	
	/**
	 * <h2>Descripción:</h2>
	 *  Metodo encargado de mapear {@link Daño} a {@link DañoVerReporteDTO}
	 * @param daño
	 * @return {@link DañoVerReporteDTO}
	 */
	public DañoVerReporteDTO toDtoDañoVerReporte(Daño daño) {
		DañoVerReporteDTO dañoVerReporteDTO=null;
		if(daño!=null) {
			mapper.typeMap(Daño.class,DañoVerReporteDTO.class)
			.addMapping(x -> x.getCliente().getNumeroIden(), DañoVerReporteDTO::setNumeroIden)
			.addMapping(x -> x.getTipoDaño().getNombre(), DañoVerReporteDTO::setTipoDano);
			dañoVerReporteDTO=mapper.map(daño, DañoVerReporteDTO.class);
		}
		return dañoVerReporteDTO;
	}
	/**
	 * <h2>Descripción:</h2>
	 *  Metodo encargado de mapear List<{@link Daño}> a List<{@link DañoVerReporteDTO}>
	 * @param List<Daño>
	 * @return List<{@link DañoVerReporteDTO}>
	 */
	//Mapero de lista entidades a DañoVerReporteDTO 
	public List<DañoVerReporteDTO> toDtoDañoVerReportes(List<Daño> daños){
		return daños.stream().map(Daño->toDtoDañoVerReporte(Daño)).collect(Collectors.toList());
	}
	
	/**
	 * <h2>Descripción:</h2>
	 *  Metodo encargado de mapear {@link DañoNuevoDTO} a {@link Daño}
	 * @param DañoNuevoDTO
	 * @return {@link Daño}
	 */
	public Daño toEntity(DañoNuevoDTO dañoNuevoDTO) {
		Daño daño=null;
		if(dañoNuevoDTO!=null) {
			daño=mapper.map(dañoNuevoDTO, Daño.class);
		}
		return daño;
	}
	
}
