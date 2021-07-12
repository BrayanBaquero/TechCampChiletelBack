package com.chiletel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chiletel.entity.TipoDaño;

/**
 * <h2>Descripción:</h2>
 * Repositorio de la entidad {@link TipoDaño}
 * @author Brayan Baquero
 */

@Repository
public interface ITipoDañoRepository extends JpaRepository<TipoDaño, Integer> {
	/**
	 * <h2>Descripción:</h2>
	 * Actualiza el tiempo de atencion y la prioridad del tipo de daño.
	 * @param nombre, tiempo, prioridad
	 * @return int  Numero de filas modificadas por la consulta
	 */
	@Modifying
	@Query("update TipoDaño u set u.tiempoAtencion=:tiempo,u.prioridad=:prioridad where u.nombre=:nombre")
	public int updateTDaño(@Param("nombre") String nombre,@Param("tiempo") int tiempo,@Param("prioridad") int prioridad);
	
	/**
	 * <h2>Descripción:</h2>
	 * Actualizar la prioridad de los tipo de daño a null, esto a que el campo es <br>
	 * de tipo unico y se deben actualizar todas las prioridades al tiempo.
	 * @return int  Numero de filas modificadas por la consulta
	 */
	@Modifying
	@Query("update TipoDaño u set u.prioridad=null")
	public int updateTDañoPrioridadNull();

	/**
	 * <h2>Descripción:</h2>
	 * Obtener tipo de daño por nombre.
	 * @param daño
	 * @return Optional<{@link TipoDaño}>
	 */
	public Optional<TipoDaño> findByNombre(String nombre);
}
