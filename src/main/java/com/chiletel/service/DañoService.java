package com.chiletel.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chiletel.dto.DañoNuevoDTO;
import com.chiletel.dto.DañoVerReporteDTO;
import com.chiletel.entity.Cliente;
import com.chiletel.entity.Daño;
import com.chiletel.entity.OrdenAtencion;
import com.chiletel.entity.TipoDaño;
import com.chiletel.exceptionHandler.NotFoundException;
import com.chiletel.mapper.DañoMapper;
import com.chiletel.repository.IClienteRepository;
import com.chiletel.repository.IDañoRepository;
import com.chiletel.repository.IOrdenAtencionRepository;
import com.chiletel.repository.ITipoDañoRepository;

@Service
public class DañoService implements IDañoService {
	
	@Autowired
	private IDañoRepository dañoRepo;
	@Autowired
	private IClienteRepository clienteRepo;
	@Autowired
	private ITipoDañoRepository tipoDañoRepo;
	@Autowired
	private IOrdenAtencionRepository ordenAtencionRepo;
	@Autowired 
	private DañoMapper dañoMapper;

	@Override
	public List<DañoVerReporteDTO> getAllDaños() {
		List<Daño> dañoVerReporteDTOs=dañoRepo.findAll();
		return dañoMapper.toDtoDañoVerReportes(dañoVerReporteDTOs);
	}

	@Override
	public void addDaño(DañoNuevoDTO dañoDTO) {
		Cliente cliente=clienteRepo.findByNumeroIden(dañoDTO.getCliente())
				.orElseThrow(()->new NotFoundException("El cliente no existe"));
		TipoDaño tipoDaño=tipoDañoRepo.findByNombre(dañoDTO.getTipoDaño())
				.orElseThrow(()->new NotFoundException("El tipo de daño a reportar no existe."));
		
		Daño daño=dañoMapper.toEntity(dañoDTO);
		daño.setCliente(cliente);
		daño.setTipoDaño(tipoDaño);
		//int idDaño=dañoRepo.save(daño).getId();
		OrdenAtencion ordenAtencion=new OrdenAtencion();
		ordenAtencion.setDaño(dañoRepo.save(daño));
		ordenAtencion.setNumOrden(UUID.randomUUID().toString());
		ordenAtencionRepo.save(ordenAtencion);
		
	}
	
	
}
