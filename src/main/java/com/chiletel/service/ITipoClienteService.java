package com.chiletel.service;

import java.util.List;

import com.chiletel.dto.TipoClienteDTO;
import com.chiletel.dto.TipoDa�oDTO;

public interface ITipoClienteService {
	public List<TipoDa�oDTO> getAllTecnicos();
	public void Update(List<TipoClienteDTO> clienteDTOs);
}
