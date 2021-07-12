package com.chiletel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chiletel.entity.OrdenAtencion;
/**
 * <h2>Descripción: </h2>
 * Repositorio de la entidad {@link OrdenAtencion}
 * @author Brayan Baquero
 */
@Repository
public interface IOrdenAtencionRepository extends JpaRepository<OrdenAtencion, Integer> {
	
	/**
	 * <h2>Descripción: </h2>
	 * Retornar las ordenes de atencion que no han sido agendadas.
	 * @param pageable
	 * @return Page<{@link OrdenAtencion}
	 */
	@Query(value = "select o from OrdenAtencion o where o.agendado=0")
	Page<OrdenAtencion> findAllOrdenesAtencion(Pageable pageable);
}
