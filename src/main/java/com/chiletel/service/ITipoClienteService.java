package com.chiletel.service;

import java.util.List;

import com.chiletel.dto.TipoClienteDTO;

/**
 * <h2>Descripci�n:</h2>
 * Interfas encargada de definir metodos de interacci�n
 * sobre DB para TipoDa�oCliente
 * @author Brayan Baquero
 */
public interface ITipoClienteService {
	/**
	 * <h2>Descripci�n:</h2>
	 * Obtener todos los tipos de cliente.
	 * @return List<{@link TipoClienteDTO}>
	 */
	public List<TipoClienteDTO> getAllTipoCliente();
	/**
	 * <h2>Descripci�n:</h2>
	 * Actualizar prioridad de los tipos de cliente.
	 * @param clienteDTOs
	 */
	public void updateTipoCliente(List<TipoClienteDTO> tipoclienteDTOs);
}
