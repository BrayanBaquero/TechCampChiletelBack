package com.chiletel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiletel.entity.TipoDaño;

@Repository
public interface ITipoDañoRepository extends JpaRepository<TipoDaño, Integer> {
	public List<TipoDaño>getByNombre(String nombre);

	public Optional<TipoDaño> findByNombre(String nombre);
}
