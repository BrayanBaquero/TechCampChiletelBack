package com.chiletel.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chiletel.dto.TecnicoDTO;
/**
 * <p>
 * Interfas encargada de definir metodos de interacci�n
 * sobre DB para TecnicoService
 * @author Brayan Baquero
 */

public interface ITecnicoService {
	/**
	 * <h2>Descripci�n:</h2>
	 * Se obtienen los tecnicos activos
	 * @param pageable
	 * @return Lista paginada de t�cnicos
	 */
	public Page<TecnicoDTO> getTecnicos(Pageable pageable);
	/**
	 * <h2>Descripci�n:</h2>
	 * Agregar t�cnico
	 * @param tecnicoDTO
	 */
	public void addTecnico(TecnicoDTO tecnicoDTO);
	/**
	 * <h2>Descripci�n:</h2>
	 * Actualizar datos de un t�cnico
	 * @param ident Identificaci�n de usuario (cedula)
	 * @param tecnicoDTO
	 */
	public void updateTecnico(Long ident,TecnicoDTO tecnicoDTO);
	/**
	 * <h2>Descripci�n:</h2>
	 * Borrar tecnico
	 * @param ident Identificaci�n de usuario (cedula)
	 */
	public void deleteTecnico(Long ident);
	/**
	 * <h2>Descripci�n:</h2>
	 * @param ident Identificaci�n de usuario (cedula)
	 * @return TecnicoDTO 
	 */
	public TecnicoDTO getTecnicoByIdentificacion(Long ident);
}
