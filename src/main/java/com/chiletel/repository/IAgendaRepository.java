package com.chiletel.repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chiletel.dto.AgendaEventosDTO;
import com.chiletel.entity.Agenda;

@Repository
public interface IAgendaRepository  extends JpaRepository<Agenda, Integer>{
	/*
	@Query(value = "select tc.nombre as tec_nombre,tc.identificacion as tec_iden, coalesce(ag.id_orden_atencion,0) as ord_id,ag.h_inicio as inicio,ag.h_final as final from tecnicos tc left join agendas ag\r\n"
			+ "on (tc.id_tecnico=ag.id_tecnico)\r\n"
			+ "and to_char(ag.fecha,'D')>=2 and to_char(ag.fecha,'D')<=6 and tc.borrado=0\r\n"
			+ "where tc.id_tecnico=:id \r\n"
			+ "order by tec_nombre,ag.h_final",nativeQuery = true)
*/
	
	@Query(value = "select  coalesce(ag.id_orden_atencion,0) as ord_id,ag.h_inicio as inicio,ag.h_final as final from tecnicos tc left join agendas ag\r\n"
			+ "on (tc.id_tecnico=ag.id_tecnico)\r\n"
			+ "and to_char(ag.fecha,'D')>=2 and to_char(ag.fecha,'D')<=6 and tc.borrado=0\r\n"
			+ "where tc.identificacion=:ident \r\n"
			+ "order by ag.h_final asc",nativeQuery = true)
	List<AgendaEventosDTO> findAllAgTec(@Param("ident") BigInteger ident);	
	
	//Obtener agenda de tecnico en un rango de fecha especifico
	@Query(value = "select  coalesce(ag.id_orden_atencion,0) as ord_id,ag.h_inicio as inicio,ag.h_final as final,cl.nombre as nombreCliente,cl.apellido as apellidoCliente,cl.identificacion,cl.direccion as Direccion,ti.nombre as tipoIncidencia from tecnicos tc \r\n"
			+ "    inner join agendas ag on (tc.id_tecnico=ag.id_tecnico)\r\n"
			+ "			and ag.fecha>=:fi \r\n"
			+ "         and ag.fecha<=:ff and tc.borrado=0\r\n"
			+ "    inner join ordenes_atencion ort on (ort.id_orden_atencion=ag.id_orden_atencion)\r\n"
			+ "    inner join incidencias i on(i.id_incidencia=ort.id_incidencia)\r\n"
			+ "    inner join clientes cl on(cl.id_cliente=i.id_cliente)\r\n"
			+ "    inner join tipos_incidencia ti on (i.id_tipo_incidencia=ti.id_tipo_incidencia)\r\n"
			+ "    where tc.identificacion=:ident\r\n"
			+ "			order by ag.h_final asc",nativeQuery = true)
	List<AgendaEventosDTO> findAgendaTec(@Param("ident") BigInteger ident,@Param("fi") Date fi, @Param("ff") Date ff);
	
	//Obtener datos adicionales de agenda para ver en el front cuando el usuario de click en el evento del calendario
	
}
