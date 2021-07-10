package com.chiletel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chiletel.entity.OrdenAtencion;

@Repository
public interface IOrdenAtencionRepository extends JpaRepository<OrdenAtencion, Integer> {
	
	@Query(value = "select o from OrdenAtencion o where o.agendado=1")
	Page<OrdenAtencion> findAllOrdenesAtencion(Pageable pageable);
}
