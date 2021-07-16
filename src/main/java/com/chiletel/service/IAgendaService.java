package com.chiletel.service;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.chiletel.dto.AgendaEventosDTO;
import com.chiletel.dto.AgendaTecnicosDTO;

/**
 * <h2>Descripción:</h2>
 * Interfaz que se encarga de definir los metodos a implementar en AgendaService
 * @author Brayan Baquero
 *
 */
public interface IAgendaService {
	/**
	 * <h2>Descripción:</h2>
	 * Obtener nombre, apellido e identificación de los técnicos actualmente activos.
	 * @param pageable
	 * @return Page<{@link AgendaTecnicosDTO}>
	 */
	public Page<AgendaTecnicosDTO> getAll(Pageable pageable);
	
	/**
	 * <h2>Descripción:</h2>
	 * Buscar y retornar la agenda de un técnico en un rango de fecha especifico.
	 * @param ident
	 * @param fecha_inicio
	 * @param fecha_final
	 * @return List<{@link AgendaEventosDTO}>
	 */
	public List<AgendaEventosDTO> getAgendaTecnico (Long ident,Date fecha_inicio,Date fecha_final);
	
	
	/**
	 * <h2>Descripción:</h2>
	 * Se encarga de ejecutar el procedimiento almacenado, y retorna un valor entero 1 cuando<br>
	 * el procedimiento ha terminado de ejecutarce.
	 * @return int
	 */
	public int genAgenda();
}
