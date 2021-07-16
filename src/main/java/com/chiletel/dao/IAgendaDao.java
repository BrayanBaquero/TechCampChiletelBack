package com.chiletel.dao;

import java.util.Date;
import java.util.List;

import com.chiletel.dto.AgendaEventosDTO;

/**
 * <h2>Descripci�n:</h2>
 * Definir los metodos que se usaran para interactuar con la entidad agenda<br>
 * lo cual se realiza mediate jbdc
 * @author Brayan Baquero
 *
 */
public interface IAgendaDao {
	
	/**
	 * <h2>Descripci�n:</h2>
	 * Metodo encargado de activar el proceso masivo que agenda las ordenes de atencio�n a los t�cnicos.
	 * @return int
	 */
	public int agendarOrdenes();
	
	/**
	 * <h2>Descripci�n:</h2>
	 * Este metodo se encarga de obtener los eventos (ordenes de atenci�n agendadas) de un tecnico <br>
	 * en un periodo de tiempo especifico.
	 * @return list<{@link AgendaEventosDTO}>
	 */
	public List<AgendaEventosDTO> getAllAgendaEventos(Long ident ,Date inicio,Date final_);
}
