package com.chiletel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chiletel.entity.TipoDaño;

@Repository
public interface ITipoDañoRepository extends JpaRepository<TipoDaño, Integer> {
	public List<TipoDaño>getByNombre(String nombre);

	public Optional<TipoDaño> findByNombre(String nombre);
	
	@Modifying
	@Query("update TipoDaño u set u.tiempoAtencion=:tiempo,u.prioridad=:prioridad where u.nombre=:nombre")
	public int updateTDaño(@Param("nombre") String nombre,@Param("tiempo") int tiempo,@Param("prioridad") int prioridad);
	
	@Modifying
	@Query("update TipoDaño u set u.prioridad=null")
	public int updateTDañoPrioridadNull();
}
