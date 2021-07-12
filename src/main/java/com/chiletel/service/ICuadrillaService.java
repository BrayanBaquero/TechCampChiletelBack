package com.chiletel.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chiletel.dto.CuadrillaDTO;
import com.chiletel.dto.NuevaCuadrillaDTO;

/**
 *<h2>Descripción:</h2>
 * Interfas encargada de definir metodos de interaccion
 * sobre DB para CuadrillaService
 * 
 * @author Brayan Baquero
 */
public interface ICuadrillaService {
	/**
	 * <h2>Descripción:</h2>
	 * Se obtienen las cuadrillas activas
	 * @param pageable
	 * @return Lista paginada {@link CuadrillaDTO}
	 */
	public Page<CuadrillaDTO> getCuadrillas(Pageable pageable);
	/**
	 * <h2>Descripción:</h2>
	 * Agregar cuadrilla
	 * @param NuevaCuadrillaDTO
	 */
	public void addCuadrilla(NuevaCuadrillaDTO nuevaCuadrillaDTO);
	/**
	 * <h2>Descripción:</h2>
	 * Actualizar datos cuadrilla
	 * @param nombre Nombre de la cuadrilla a actualizar
	 * @param NuevaCuadrillaDTO
	 */
	public void updateCuadrilla(String nombre, NuevaCuadrillaDTO nuevaCuadrillaDTO);
	/**
	 * <h2>Descripción:</h2>
	 * Borrar cuadrilla
	 * @param nombre Nombre de la cuadrilla
	 */
	public void deleteCuadrilla(String nombre);
	
	/**
	 * <h2>Descripción:</h2>
	 * Obtener Lista de nombres de las cuadrillas
	 * @return
	 */
	public List<String> getAllNombres();
}
