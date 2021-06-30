package com.chiletel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiletel.entity.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer>{
	
	Optional<Cliente> findByNumeroIden(int numeroIden);
}
