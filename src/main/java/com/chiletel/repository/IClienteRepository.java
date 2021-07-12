package com.chiletel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiletel.entity.Cliente;

/**
 * <h2>Descripci�n: </h2>
 * Repositorio de clientes
 * @author Brayan Baquero
 */
@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer>{
	
	/**
	 * <h2>Descripci�n: </h2>
	 * Buscar a cliente por identificaci�n.
	 * @param numeroIden
	 * @return
	 */
	Optional<Cliente> findByNumeroIden(long numeroIden);
}
