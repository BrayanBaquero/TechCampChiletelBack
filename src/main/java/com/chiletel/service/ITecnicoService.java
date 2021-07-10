package com.chiletel.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chiletel.dto.TecnicoDTO;

public interface ITecnicoService {
	public Page<TecnicoDTO> obtenerTecnicos(Pageable pageable);
	public void AddTecnico(TecnicoDTO tecnicoDTO);
	public void UpdateTecnico(BigInteger ident,TecnicoDTO tecnicoDTO);
	public void DeleteTecnico(BigInteger ident);
	public TecnicoDTO getTecnicoByIdentificacion(BigInteger ident);
}
