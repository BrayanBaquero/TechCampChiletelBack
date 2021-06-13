package com.chiletel.service;

import java.util.List;

import com.chiletel.dto.TecnicoDTO;

public interface ITecnicoService {
	public List<TecnicoDTO> obtenerTecnicos();
	public void AddTecnico();
	public void UpdateTecnico(String id);
	public void DeleteTecnico();
	public TecnicoDTO getTecnicoByIdentificacion(String number);
}
