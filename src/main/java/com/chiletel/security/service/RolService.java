package com.chiletel.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chiletel.security.entity.Rol;
import com.chiletel.security.enums.RolNombre;
import com.chiletel.security.repositoty.IRolRepositoty;

@Service
@Transactional
public class RolService {
	@Autowired
	IRolRepositoty rolRepositoty;
	
	public Optional<Rol> getByRolNombre(RolNombre rolNombre){
		return rolRepositoty.findByRolNombre(rolNombre);
	}

}
