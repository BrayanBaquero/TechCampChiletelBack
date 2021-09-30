package com.chiletel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiletel.entity.Dano;

@Repository
public interface IDanoRepository extends JpaRepository<Dano, Integer> {

}
