package com.chiletel.service;

import java.util.List;

import com.chiletel.dto.TipoDa�oDTO;

public interface ITipoDa�oService {
	public List<TipoDa�oDTO> getAllTipoDa�o();
	public void updateTipoDa�o(List<TipoDa�oDTO> tipoDa�oDTOs);
}
