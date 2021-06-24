package com.chiletel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.chiletel.dto.TipoDañoDTO;
import com.chiletel.entity.TipoDaño;
import com.chiletel.mapper.TipoDañoMapper;

@SpringBootTest
class mapperTipoDañoTests {
	@Autowired
	private TipoDañoMapper tipoDañoMapper;
	
	@Test
	void test() {
		TipoDañoDTO tipoDañoDTO=new TipoDañoDTO();
		tipoDañoDTO.setNombre("esporadica");
		tipoDañoDTO.setPrioridad(1);
		tipoDañoDTO.setTiempo(1);
		
		TipoDaño tipoDaño=tipoDañoMapper.toEntity(tipoDañoDTO);
		
		assertEquals(tipoDañoDTO, tipoDaño);
	}

}
