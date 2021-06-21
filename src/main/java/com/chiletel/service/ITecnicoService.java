package com.chiletel.service;

import java.util.List;

import com.chiletel.dto.TecnicoDTO;

public interface ITecnicoService {
	public List<TecnicoDTO> obtenerTecnicos();
	public void AddTecnico(TecnicoDTO tecnicoDTO);
	public void UpdateTecnico(int ident,TecnicoDTO tecnicoDTO);
	public void DeleteTecnico(int ident);
	public TecnicoDTO getTecnicoByIdentificacion(int ident);
}
