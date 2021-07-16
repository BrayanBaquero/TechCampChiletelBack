package com.chiletel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chiletel.dao.AgendaDao;
import com.chiletel.dto.AgendaEventosDTO;

/**
 * <h2>Descripción:</h2>
 * Clase encargada de mapear el query realizado en {@link AgendaDao} al dto correspondiente.
 * @author Brayan Baquero
 *
 */
public class AgendaEventosRowMapper implements RowMapper<AgendaEventosDTO> {

	@Override
	public AgendaEventosDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		AgendaEventosDTO agendaEventosDTOO= new AgendaEventosDTO();
		agendaEventosDTOO.setNombreCliente(rs.getString("nombreCliente"));
		agendaEventosDTOO.setOrd_Id(rs.getInt("ord_id"));
		agendaEventosDTOO.setInicio(rs.getString("Inicio"));
		agendaEventosDTOO.setFinal(rs.getString("final"));
		agendaEventosDTOO.setNombreCliente(rs.getString("nombreCliente"));
		agendaEventosDTOO.setApellidoCliente(rs.getString("apellidoCliente"));
		agendaEventosDTOO.setIdentificacion(rs.getLong("identificacion"));
		agendaEventosDTOO.setTipoIncidencia(rs.getString("tipoIncidencia"));
		agendaEventosDTOO.setDireccion(rs.getString("direccion"));
		return agendaEventosDTOO;
	}

	
}
