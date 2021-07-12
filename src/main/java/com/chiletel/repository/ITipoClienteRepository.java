package com.chiletel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chiletel.entity.TipoCliente;
/**
 * <h2>Descripción:</h2>
 * Repositorio de la entidad {@link TipoCliente}
 * @author Brayan Baquero
 */
@Repository
public interface ITipoClienteRepository extends JpaRepository<TipoCliente, Integer>{
	/**
	 * <h2>Descripción:</h2>
	 * Obtener tipo de daño por nombre.
	 * @param nombre
	 * @return Optional<{@link TipoCliente}>
	 */
	public Optional<TipoCliente> findByNombre(String nombre);
	
	/**
	 * <h2>Descripción:</h2>
	 * Actualiza la prioridad de atencion por tipo de cliente..
	 * @param nombre, prioridad
	 * @return int  Numero de filas modificadas por la consulta
	 */
	@Modifying
	@Query("update TipoCliente tc set tc.prioridad=:prioridad where tc.nombre=:nombre")
	public int updateTClientePrioridad(@Param("nombre") String nombre,@Param("prioridad") int prioridad);
	
	/**
	 * <h2>Descripción:</h2>
	 * Actualizar la prioridad de los tipo de daño a null, esto a que el campo es <br>
	 * de tipo unico y se deben actualizar todas las prioridades al tiempo.
	 * @return int  Numero de filas modificadas por la consulta
	 */
	@Modifying
	@Query("update TipoCliente tc set tc.prioridad=null")
	public int updateTClientePrioridadNull();

}
