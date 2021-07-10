package com.chiletel.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chiletel.dto.OrdenAtencionDTO;

public interface IOrdenAtencionService {

	public Page<OrdenAtencionDTO> getAllOrdenesAtencion(Pageable pageable);

}
