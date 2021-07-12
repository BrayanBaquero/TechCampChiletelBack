package com.chiletel.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.chiletel.dto.Da�oNuevoDTO;
import com.chiletel.dto.Da�oVerReporteDTO;
import com.chiletel.entity.Da�o;

/**
 * <h2>Descripci�n:</h2>
 * Clase encarga de realizar mapeo de datos entre la entidad {@link Da�o} y el dto {@link Da�oVerReporteDTO} <br>
 * y {@link Da�oNuevoDTO}
 * usando la libreria {@link ModelMapper}
 * @author Brayan Baquero
 */
@Component("da�oMapper")
@Configuration
public class Da�oMapper {

	@Autowired
	private ModelMapper mapper;
	
	/**
	 * <h2>Descripci�n:</h2>
	 *  Metodo encargado de mapear {@link Da�o} a {@link Da�oVerReporteDTO}
	 * @param da�o
	 * @return {@link Da�oVerReporteDTO}
	 */
	public Da�oVerReporteDTO toDtoDa�oVerReporte(Da�o da�o) {
		Da�oVerReporteDTO da�oVerReporteDTO=null;
		if(da�o!=null) {
			mapper.typeMap(Da�o.class,Da�oVerReporteDTO.class)
			.addMapping(x -> x.getCliente().getNumeroIden(), Da�oVerReporteDTO::setNumeroIden)
			.addMapping(x -> x.getTipoDa�o().getNombre(), Da�oVerReporteDTO::setTipoDano);
			da�oVerReporteDTO=mapper.map(da�o, Da�oVerReporteDTO.class);
		}
		return da�oVerReporteDTO;
	}
	/**
	 * <h2>Descripci�n:</h2>
	 *  Metodo encargado de mapear List<{@link Da�o}> a List<{@link Da�oVerReporteDTO}>
	 * @param List<Da�o>
	 * @return List<{@link Da�oVerReporteDTO}>
	 */
	//Mapero de lista entidades a Da�oVerReporteDTO 
	public List<Da�oVerReporteDTO> toDtoDa�oVerReportes(List<Da�o> da�os){
		return da�os.stream().map(Da�o->toDtoDa�oVerReporte(Da�o)).collect(Collectors.toList());
	}
	
	/**
	 * <h2>Descripci�n:</h2>
	 *  Metodo encargado de mapear {@link Da�oNuevoDTO} a {@link Da�o}
	 * @param Da�oNuevoDTO
	 * @return {@link Da�o}
	 */
	public Da�o toEntity(Da�oNuevoDTO da�oNuevoDTO) {
		Da�o da�o=null;
		if(da�oNuevoDTO!=null) {
			da�o=mapper.map(da�oNuevoDTO, Da�o.class);
		}
		return da�o;
	}
	
}
