package com.chiletel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chiletel.entity.TipoDa�o;

@Repository
public interface ITipoDa�oRepository extends JpaRepository<TipoDa�o, Integer> {
	public List<TipoDa�o>getByNombre(String nombre);

	public Optional<TipoDa�o> findByNombre(String nombre);
	
	@Modifying
	@Query("update TipoDa�o u set u.tiempoAtencion=:tiempo,u.prioridad=:prioridad where u.nombre=:nombre")
	public int updateTDa�o(@Param("nombre") String nombre,@Param("tiempo") int tiempo,@Param("prioridad") int prioridad);
	
	@Modifying
	@Query("update TipoDa�o u set u.prioridad=null")
	public int updateTDa�oPrioridadNull();
}
