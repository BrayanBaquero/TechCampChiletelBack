package com.chiletel.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chiletel.dto.CuadrillasTecnicos;
import com.chiletel.entity.Cuadrilla;
/**
 * <h2>Descripción:</h2>
 * Repositorio de la entidad {@link Cuadrilla}
 * @author Brayan Baquero
 */
@Repository
@Transactional
public interface ICuadrillaRepository extends JpaRepository<Cuadrilla, Integer> {
	
	/**
	 * <h2>Descripción:</h2>
	 * Se encarga validar si la cuadrilla con determinado nombre existe.
	 * @param  nombre
	 * @return Opcional<{@link Cuadrilla}>
	 */
	Optional<Cuadrilla> findByNombre(String cuadrilla);
	boolean existsByNombre(String nombre);
	
	/**
	 * <h2>Descripción:</h2>
	 * Se encarga Encontrar cuadrillas con el numero de tecnicos que pertenece a cada una,
	 * el resultado se mapea en la interfaz de proyeccion {@link CuadrillasTecnicos} .
	 * @return List<{@link CuadrillasTecnicos}>
	 */
	@Query(value = "select c.nombre,c.id_cuadrilla as id, count(tc.id_cuadrilla) as miembros from cuadrillas c \r\n"
			+ "left join tecnicos tc \r\n"
			+ "    on tc.id_cuadrilla=c.id_cuadrilla\r\n"
			+ "where tc.borrado=0\r\n"
			+ "group by c.nombre,c.id_cuadrilla\r\n"
			+ "order by c.id_cuadrilla",nativeQuery = true)
	List<CuadrillasTecnicos> getAllCuadrillasConTecnicos();
	
	/**
	 * <h2>Descripción:</h2>
	 * Validar si la cuadrilla con el nombre indicado existe.
	 * @return {@link Boolean}
	 */
	boolean existsBynombre(String nombre);
	
	/**
	 * <h2>Descripción:</h2>
	 * Obtener la cuadrilla que tenga el nombre indicado y que se encuentre activa borraro=0.
	 * @param nombre
	 * @return Opcional<{@link Cuadrilla}>
	 */
	@Query(value = "SELECT * FROM CUADRILLAS C WHERE C.BORRADO = 0 and C.nombre = :nombre", nativeQuery = true)
	Optional<Cuadrilla> findBynombre(@Param("nombre") String  nombre);
	
	/**
	 * <h2>Descripción:</h2>
	 * Obtener cuadrillas que no han sido eliminadas de manera logica mediante un nativeQuery.
	 * @param pageable
	 * @return Page<{@link Cuadrilla}>
	 */ 
	@Query(value = "SELECT * FROM CUADRILLAS C WHERE C.BORRADO = 0", nativeQuery = true)
	Page<Cuadrilla> getAllCuadrillasActivas(Pageable pageable);
	
	
	/**
	 * <h2>Descripción:</h2>
	 * Obtener los nombre de las cuadrillas activas mediante JPQL.
	 * @return List<{@link String}>
	 */
	@Query("SELECT c.nombre FROM Cuadrilla c where c.borrado=0")
	List<String> getAllNombres();

}
