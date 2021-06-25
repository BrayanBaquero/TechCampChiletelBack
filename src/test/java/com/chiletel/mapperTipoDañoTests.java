package com.chiletel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.chiletel.dto.TipoDa�oDTO;
import com.chiletel.entity.TipoDa�o;
import com.chiletel.mapper.TipoDa�oMapper;

@SpringBootTest
class mapperTipoDa�oTests {
	@Autowired
	private TipoDa�oMapper tipoDa�oMapper;
	
	@Test
	void test() {
		TipoDa�oDTO tipoDa�oDTO=new TipoDa�oDTO();
		tipoDa�oDTO.setNombre("esporadica");
		tipoDa�oDTO.setPrioridad(1);
		tipoDa�oDTO.setTiempo(1);
		
		TipoDa�o tipoDa�o=tipoDa�oMapper.toEntity(tipoDa�oDTO);
		
		assertEquals(tipoDa�oDTO, tipoDa�o);
	}

}
