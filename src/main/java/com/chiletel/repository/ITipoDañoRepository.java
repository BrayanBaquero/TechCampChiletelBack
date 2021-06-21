package com.chiletel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiletel.entity.TipoDa�o;

@Repository
public interface ITipoDa�oRepository extends JpaRepository<TipoDa�o, Integer> {
	public List<TipoDa�o>getByNombre(String nombre);

	public Optional<TipoDa�o> findByNombre(String nombre);
}
