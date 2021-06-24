package com.chiletel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chiletel.entity.TipoCliente;

@Repository
public interface ITipoClienteRepository extends JpaRepository<TipoCliente, Integer>{
	
	
	Optional<TipoCliente> findByNombre(String nombre);
	
	@Modifying
	@Query("update TipoCliente tc set tc.prioridad=:prioridad where tc.nombre=:nombre")
	public int updateTClientePrioridad(@Param("nombre") String nombre,@Param("prioridad") int prioridad);
	
	@Modifying
	@Query("update TipoCliente tc set tc.prioridad=null")
	public int updateTClientePrioridadNull();

}
