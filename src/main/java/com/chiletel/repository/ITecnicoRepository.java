package com.chiletel.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chiletel.entity.Tecnico;


@Repository
public interface ITecnicoRepository extends JpaRepository<Tecnico, Integer> {

	@Query(value = "SELECT * FROM TECNICOS T WHERE T.BORRADO = 0 and T.IDENTIFICACION = :identificacion", nativeQuery = true)
	Optional<Tecnico> findBynumeroIden(@Param("identificacion") BigInteger  identificacion);
	
	boolean existsBynumeroIden(BigInteger identificacion);
	
	@Query(value = "SELECT * FROM TECNICOS T WHERE T.BORRADO = 0", nativeQuery = true)
	Page<Tecnico> getAllTecnicosActivos(Pageable pageable);
	
	
}
