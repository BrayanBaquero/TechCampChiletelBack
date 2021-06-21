package com.chiletel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiletel.entity.Cuadrilla;
import com.chiletel.entity.TipoDaño;

@Repository
public interface ICuadrillaRepository extends JpaRepository<Cuadrilla, Integer> {

	Optional<Cuadrilla> findByNombre(String cuadrilla);
	boolean existsByNombre(String nombre);

}
