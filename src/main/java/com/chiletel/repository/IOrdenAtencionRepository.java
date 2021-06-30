package com.chiletel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiletel.entity.OrdenAtencion;

@Repository
public interface IOrdenAtencionRepository extends JpaRepository<OrdenAtencion, Integer> {

}
