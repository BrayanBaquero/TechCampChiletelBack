package com.chiletel.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chiletel.dto.CuadrillaDTO;
import com.chiletel.entity.Cuadrilla;
import com.chiletel.entity.CuadrillasTecnicos;
import com.chiletel.entity.Tecnico;
import com.chiletel.entity.TipoDaño;

@Repository
public interface ICuadrillaRepository extends JpaRepository<Cuadrilla, Integer> {

	Optional<Cuadrilla> findByNombre(String cuadrilla);
	boolean existsByNombre(String nombre);
	
	/*Encontrar cuadrillas con el numero de tecnicos que pertenece a cada una,
	 * el resultado se mapea en la interfaz cuadrillasTecnicos*/
	@Query(value = "select c.nombre,c.id_cuadrilla as id, count(tc.id_cuadrilla) as miembros from cuadrillas c \r\n"
			+ "left join tecnicos tc \r\n"
			+ "    on tc.id_cuadrilla=c.id_cuadrilla\r\n"
			+ "where tc.borrado=0\r\n"
			+ "group by c.nombre,c.id_cuadrilla\r\n"
			+ "order by c.id_cuadrilla",nativeQuery = true)
	List<CuadrillasTecnicos> getAllCuadrillasConTecnicos();
	
	boolean existsBynombre(String nombre);
	
	@Query(value = "SELECT * FROM CUADRILLAS C WHERE C.BORRADO = 0 and C.nombre = :nombre", nativeQuery = true)
	Optional<Cuadrilla> findBynombre(@Param("nombre") String  nombre);
	
	//Obtener cuadrillas que no han sido eliminadas de manera logica 
	@Query(value = "SELECT * FROM CUADRILLAS C WHERE C.BORRADO = 0", nativeQuery = true)
	List<Cuadrilla> getAllCuadrillasActivas();
	
	@Query("SELECT c.nombre FROM Cuadrilla c where c.borrado=0")
	List<String> getAllNombres();

}
