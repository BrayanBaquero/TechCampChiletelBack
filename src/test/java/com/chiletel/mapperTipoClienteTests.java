package com.chiletel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.chiletel.dto.TipoClienteDTO;
import com.chiletel.entity.TipoCliente;
import com.chiletel.mapper.TipoClienteMapper;
@SpringBootTest
class mapperTipoClienteTests {
	
	@Autowired
	private TipoClienteMapper tClienteMapper;
	@Test
	void toDto() {
		
		TipoCliente tipoCliente=new TipoCliente();
		tipoCliente.setNombre("Prueba");;
		tipoCliente.setPrioridad(1);
		
		TipoClienteDTO tipoClienteDTO=tClienteMapper.toDto(tipoCliente);
		
		assertEquals(tipoCliente, tipoClienteDTO);
	}

}
