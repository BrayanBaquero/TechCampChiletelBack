package com.chiletel.service;

import java.util.List;

import com.chiletel.dto.CuadrillaDTO;
import com.chiletel.dto.NuevaCuadrillaDTO;

public interface ICuadrillaService {
	public List<CuadrillaDTO> getAll();
	public void add(NuevaCuadrillaDTO nuevaCuadrillaDTO);
	public void update(String nombre, NuevaCuadrillaDTO nuevaCuadrillaDTO);
	public void delete(String nombre);
}
