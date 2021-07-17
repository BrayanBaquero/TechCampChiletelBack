package com.chiletel.repository;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chiletel.dto.AgendaTecnicosDTO;
import com.chiletel.entity.Tecnico;

/**
 * <h2>Descripción:</h2>
 * Repositorio de la entidad {@link Tecnico}
 * @author Brayan Baquero
 *
 */
@Repository
public interface ITecnicoRepository extends JpaRepository<Tecnico, Integer> {
	
	/**
	 * <h2>Descripción:</h2>
	 * Se encarga de buscar al tecnico en la DB por identificacion y validar si se 
	 * encuentra activo  borrado=0 mediante una nativeQuery
	 * @param 
	 * @return Opcional<{@link Tecnico}>
	 */
	@Query(value = "SELECT /*+ index(T, IDX_TECNICOS_01)*/ * FROM TECNICOS T WHERE T.BORRADO = 0 and T.IDENTIFICACION = :identificacion", nativeQuery = true)
	Optional<Tecnico> findBynumeroIden(@Param("identificacion")Long  identificacion);
	
	/**
	 * <h2>Descripción:</h2>
	 * Buscar por identificación si técnico existe.
	 * @param identificacion
	 * @return boolean
	 */
	boolean existsBynumeroIden(Long identificacion);
	
	/**
	 * <h2>Descripción:</h2>
	 * Mediante nativeQuery obtener los técnicos que encuentran activos y paginarlos.
	 * @param pageable
	 * @return Page<{@link Tecnico}>
	 */
	@Query(value = "SELECT * FROM TECNICOS T WHERE T.BORRADO = 0", nativeQuery = true)
	Page<Tecnico> getAllTecnicosActivos(Pageable pageable);
	
	/**
	 * <h2>Descripción:</h2>
	 * Borrar las cuadrillas que tengan asignadas los técnicos.
	 * @param id
	 * @return
	 */
	@Transactional
	@Modifying
	@Query(value = "UPDATE Tecnicos t set t.id_cuadrilla=null where t.id_cuadrilla=:id",nativeQuery = true)
	int updateCuadrillaTecnicos(@Param("id") int id);
	
	 /**
	  * <h2>Descripción:</h2>
	  * Obtener nombre, apellido, y identificacion de técnicos en uns interface de proyección,<br>
	  * para mostrarlos en la agenda.
	  * @param pageable
	  * @return
	  */
	@Query(value="SELECT t.nombre as nombre,t.apellido as apellido,t.numeroIden as numeroIden from Tecnico t where t.borrado=0")
	Page<AgendaTecnicosDTO> findAllBy(Pageable pageable);
}
