package com.chiletel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.chiletel.dto.TipoDanoDTO;
import com.chiletel.entity.TipoDano;
import com.chiletel.mapper.TipoDanoMapper;

@SpringBootTest
class mapperTipoDanoTests {
	@Autowired
	private TipoDanoMapper tipoDanoMapper;
	
	@Test
	void test() {
		TipoDanoDTO tipoDanoDTO=new TipoDanoDTO();
		tipoDanoDTO.setNombre("esporadica");
		tipoDanoDTO.setPrioridad(1);
		tipoDanoDTO.setTiempo(1);
		
		TipoDano tipoDano=tipoDanoMapper.toEntity(tipoDanoDTO);
		
		assertEquals(tipoDanoDTO, tipoDano);
	}

}
