package com.chiletel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chiletel.entity.Tecnico;

import io.swagger.v3.oas.annotations.Parameter;

@Repository
public interface ITecnicoRepository extends JpaRepository<Tecnico, Integer> {

	@Query(value = "SELECT * FROM TECNICOS T WHERE T.BORRADO = 0 and T.IDENTIFICACION = :identificacion", nativeQuery = true)
	Optional<Tecnico> findBynumeroIden(@Param("identificacion") int  identificacion);
	
	boolean existsBynumeroIden(int identificacion);
	
	@Query(value = "SELECT * FROM TECNICOS T WHERE T.BORRADO = 0", nativeQuery = true)
	 List<Tecnico> findAllTecnicosActivos();
	
}
