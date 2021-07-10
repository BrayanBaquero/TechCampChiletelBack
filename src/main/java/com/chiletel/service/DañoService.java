package com.chiletel.service;


import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chiletel.dto.Da�oNuevoDTO;
import com.chiletel.dto.Da�oVerReporteDTO;
import com.chiletel.entity.Cliente;
import com.chiletel.entity.Da�o;
import com.chiletel.entity.OrdenAtencion;
import com.chiletel.entity.TipoDa�o;
import com.chiletel.exceptionHandler.NotFoundException;
import com.chiletel.mapper.Da�oMapper;
import com.chiletel.repository.IClienteRepository;
import com.chiletel.repository.IDa�oRepository;
import com.chiletel.repository.IOrdenAtencionRepository;
import com.chiletel.repository.ITipoDa�oRepository;

@Service
public class Da�oService implements IDa�oService {
	
	@Autowired
	private IDa�oRepository da�oRepo;
	@Autowired
	private IClienteRepository clienteRepo;
	@Autowired
	private ITipoDa�oRepository tipoDa�oRepo;
	@Autowired
	private IOrdenAtencionRepository ordenAtencionRepo;
	@Autowired 
	private Da�oMapper da�oMapper;

	@Override
	public List<Da�oVerReporteDTO> getAllDa�os() {
		List<Da�o> da�oVerReporteDTOs=da�oRepo.findAll();
		return da�oMapper.toDtoDa�oVerReportes(da�oVerReporteDTOs);
	}

	@Override
	public void addDa�o(Da�oNuevoDTO da�oDTO) {
		Cliente cliente=clienteRepo.findByNumeroIden(da�oDTO.getCliente())
				.orElseThrow(()->new NotFoundException("El cliente no existe"));
		TipoDa�o tipoDa�o=tipoDa�oRepo.findByNombre(da�oDTO.getTipoDa�o())
				.orElseThrow(()->new NotFoundException("El tipo de da�o a reportar no existe."));
		Da�o da�o=da�oMapper.toEntity(da�oDTO);
		da�o.setCliente(cliente);
		da�o.setTipoDa�o(tipoDa�o);
		//int idDa�o=da�oRepo.save(da�o).getId();
		OrdenAtencion ordenAtencion=new OrdenAtencion();
		ordenAtencion.setDa�o(da�oRepo.save(da�o));
		ordenAtencion.setNumOrden(UUID.randomUUID().toString());
		ordenAtencionRepo.save(ordenAtencion);
		
	}

	@Override
	public Page<Da�oVerReporteDTO> getPagenDa�os(Pageable pageable) {
		Page<Da�o> da�oVRPage=da�oRepo.findAll(pageable);
		List<Da�oVerReporteDTO> da�oVRDto=da�oMapper.toDtoDa�oVerReportes(da�oVRPage.getContent());
		return new PageImpl<Da�oVerReporteDTO>(da�oVRDto,pageable,da�oVRPage.getTotalElements());
	}
	
	
}
