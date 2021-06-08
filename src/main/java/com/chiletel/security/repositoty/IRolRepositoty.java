package com.chiletel.security.repositoty;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiletel.security.entity.Rol;
import com.chiletel.security.enums.RolNombre;

@Repository
public interface IRolRepositoty extends JpaRepository<Rol, Integer> {
	Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
