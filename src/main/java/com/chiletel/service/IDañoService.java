package com.chiletel.service;

import java.util.List;

import com.chiletel.dto.DañoDTO;

public interface IDañoService {

	public List<DañoDTO> getAllDaños();

	public void addDaño(DañoDTO dañoDTO);

}
