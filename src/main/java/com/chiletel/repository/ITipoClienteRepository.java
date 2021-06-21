package com.chiletel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiletel.entity.TipoCliente;

@Repository
public interface ITipoClienteRepository extends JpaRepository<TipoCliente, Integer>{

}