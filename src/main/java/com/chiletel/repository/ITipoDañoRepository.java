package com.chiletel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chiletel.entity.TipoDa�o;

/**
 * <h2>Descripci�n:</h2>
 * Repositorio de la entidad {@link TipoDa�o}
 * @author Brayan Baquero
 */

@Repository
public interface ITipoDa�oRepository extends JpaRepository<TipoDa�o, Integer> {
	/**
	 * <h2>Descripci�n:</h2>
	 * Actualiza el tiempo de atencion y la prioridad del tipo de da�o.
	 * @param nombre, tiempo, prioridad
	 * @return int  Numero de filas modificadas por la consulta
	 */
	@Modifying
	@Query("update TipoDa�o u set u.tiempoAtencion=:tiempo,u.prioridad=:prioridad where u.nombre=:nombre")
	public int updateTDa�o(@Param("nombre") String nombre,@Param("tiempo") int tiempo,@Param("prioridad") int prioridad);
	
	/**
	 * <h2>Descripci�n:</h2>
	 * Actualizar la prioridad de los tipo de da�o a null, esto a que el campo es <br>
	 * de tipo unico y se deben actualizar todas las prioridades al tiempo.
	 * @return int  Numero de filas modificadas por la consulta
	 */
	@Modifying
	@Query("update TipoDa�o u set u.prioridad=null")
	public int updateTDa�oPrioridadNull();

	/**
	 * <h2>Descripci�n:</h2>
	 * Obtener tipo de da�o por nombre.
	 * @param da�o
	 * @return Optional<{@link TipoDa�o}>
	 */
	public Optional<TipoDa�o> findByNombre(String nombre);
}
