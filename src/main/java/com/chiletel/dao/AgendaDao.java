package com.chiletel.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.chiletel.dto.AgendaEventosDTO;
import com.chiletel.exceptionHandler.BadRequestException;
import com.chiletel.mapper.AgendaEventosRowMapper;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

/**
 * <h2>Descripción:</h2>
 * Clase encargada de interactuar con la DB mediante jbc obteniendo datos relacionados <br>
 * con la agenda de trabajo de los ténicos.
 * @author Brayan Baquero
 *
 */

@Repository
@Transactional
public class AgendaDao implements IAgendaDao{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	//private static final String nombrePaquete="pkg_agendamiento_ordenes";
	private static final String spOrquestador="sp_main";
	
	@Override
	public int agendarOrdenes() {
		SimpleJdbcCall simpleJdbcCall=new SimpleJdbcCall(jdbcTemplate)
				//.withCatalogName(nombrePaquete)
				.withFunctionName(spOrquestador);
		SqlParameterSource in = new MapSqlParameterSource();
		try {
			Map<String, Object> out=simpleJdbcCall.execute(in);
			if(out!=null) {
				return (int) out.get("returnvalue");
			}
		} catch (Exception e) {
			//System.err.println(e.getMessage());
			throw new BadRequestException(e.getMessage());
		}
		
		return 0;
	}
	
	@Override
	public List<AgendaEventosDTO> getAllAgendaEventos(Long ident ,Date inicio,Date final_) {
		
		String sql= "select  /*+ use_hash(tc ag ord i  cl ti) index(tc,idx_tecnicos_01)*/ coalesce(ag.id_orden_atencion,0) as ord_id,ag.h_inicio as inicio,ag.h_final as final,cl.nombre as nombreCliente,cl.apellido as apellidoCliente,cl.identificacion,cl.direccion as Direccion,ti.nombre as tipoIncidencia from tecnicos tc \r\n"
				+ "    inner join agendas ag on (tc.id_tecnico=ag.id_tecnico)\r\n"
				+ "			and ag.fecha>=? \r\n"
				+ "         and ag.fecha<=? and tc.borrado=0\r\n"
				+ "    inner join ordenes_atencion ort on (ort.id_orden_atencion=ag.id_orden_atencion)\r\n"
				+ "    inner join incidencias i on(i.id_incidencia=ort.id_incidencia)\r\n"
				+ "    inner join clientes cl on(cl.id_cliente=i.id_cliente)\r\n"
				+ "    inner join tipos_incidencia ti on (i.id_tipo_incidencia=ti.id_tipo_incidencia)\r\n"
				+ "    where tc.identificacion=? \r\n"
				+ "			order by ag.h_final asc";
      
		SqlParameterSource parametros = new MapSqlParameterSource()
				.addValue("inicio",inicio)
				.addValue("final", final_)
				.addValue("ident", ident);
		RowMapper<AgendaEventosDTO> rowMapper = new AgendaEventosRowMapper();
		return this.jdbcTemplate.query(sql,new Object[] { inicio, final_,ident }, rowMapper);
	}
	
	

}
