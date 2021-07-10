package com.chiletel.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chiletel.dto.DañoNuevoDTO;
import com.chiletel.dto.DañoVerReporteDTO;

public interface IDañoService {

	public List<DañoVerReporteDTO> getAllDaños();

	public void addDaño(DañoNuevoDTO dañoDTO);
	
	public Page<DañoVerReporteDTO> getPagenDaños(Pageable pageable);
}
