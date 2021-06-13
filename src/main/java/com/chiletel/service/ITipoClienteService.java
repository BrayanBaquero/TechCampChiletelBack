package com.chiletel.service;

import java.util.List;

import com.chiletel.dto.TipoClienteDTO;
import com.chiletel.dto.TipoDañoDTO;

public interface ITipoClienteService {
	public List<TipoDañoDTO> getAllTecnicos();
	public void Update(List<TipoClienteDTO> clienteDTOs);
}
