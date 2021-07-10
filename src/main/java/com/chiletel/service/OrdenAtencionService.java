package com.chiletel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chiletel.dto.OrdenAtencionDTO;
import com.chiletel.entity.OrdenAtencion;
import com.chiletel.mapper.OrdenAtencionMapper;
import com.chiletel.repository.IOrdenAtencionRepository;

@Service
public class OrdenAtencionService implements IOrdenAtencionService {
	
	@Autowired
	public IOrdenAtencionRepository ordenAtencionRepo;
	@Autowired
	public OrdenAtencionMapper ordenAtencionMapper;

	@Override
	public Page<OrdenAtencionDTO> getAllOrdenesAtencion(Pageable pageable) {
		Page<OrdenAtencion> ordenAtencion=ordenAtencionRepo.findAllOrdenesAtencion(pageable);
		List<OrdenAtencionDTO> ordenAtencionDTOs=ordenAtencionMapper.toDTOs(ordenAtencion.getContent());
		return new PageImpl<OrdenAtencionDTO>(ordenAtencionDTOs,pageable,ordenAtencion.getTotalElements());
	}

}
