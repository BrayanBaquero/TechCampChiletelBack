package com.chiletel.service;

import java.util.List;

import com.chiletel.dto.Da�oNuevoDTO;
import com.chiletel.dto.Da�oVerReporteDTO;

public interface IDa�oService {

	public List<Da�oVerReporteDTO> getAllDa�os();

	public void addDa�o(Da�oNuevoDTO da�oDTO);

}
