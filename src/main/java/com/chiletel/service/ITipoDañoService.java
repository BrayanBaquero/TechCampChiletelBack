package com.chiletel.service;

import java.util.List;

import com.chiletel.dto.TipoDañoDTO;

public interface ITipoDañoService {
	public List<TipoDañoDTO> getAllTipoDaño();
	public void updateTipoDaño(List<TipoDañoDTO> tipoDañoDTOs);
}
