package com.chiletel.service;

import java.util.List;

import com.chiletel.dto.TipoClienteDTO;

public interface ITipoClienteService {
	public List<TipoClienteDTO> getAllTipoCliente();
	public void Update(List<TipoClienteDTO> clienteDTOs);
}
