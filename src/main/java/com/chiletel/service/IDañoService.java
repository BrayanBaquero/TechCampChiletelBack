package com.chiletel.service;

import java.util.List;

import com.chiletel.dto.DañoNuevoDTO;
import com.chiletel.dto.DañoVerReporteDTO;

public interface IDañoService {

	public List<DañoVerReporteDTO> getAllDaños();

	public void addDaño(DañoNuevoDTO dañoDTO);

}
