package com.chiletel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiletel.entity.Zona;

@Repository
public interface IZonaRepository extends JpaRepository<Zona,Integer> {

	Optional<Zona> findByNombre(String nombre);

}
