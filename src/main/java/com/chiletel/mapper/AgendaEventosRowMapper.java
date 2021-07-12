package com.chiletel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chiletel.dao.AgendaSpDao;
import com.chiletel.dto.AgendaEventosDTOO;

/**
 * <h2>Descripción:</h2>
 * Clase encargada de mapear el query realizado en {@link AgendaSpDao} al dto correspondiente.
 * @author Brayan Baquero
 *
 */
public class AgendaEventosRowMapper implements RowMapper<AgendaEventosDTOO> {

	@Override
	public AgendaEventosDTOO mapRow(ResultSet rs, int rowNum) throws SQLException {
		AgendaEventosDTOO agendaEventosDTOO= new AgendaEventosDTOO();
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
