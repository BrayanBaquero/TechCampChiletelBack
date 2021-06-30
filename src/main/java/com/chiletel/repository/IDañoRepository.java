package com.chiletel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiletel.entity.Daño;

@Repository
public interface IDañoRepository extends JpaRepository<Daño, Integer> {

}
