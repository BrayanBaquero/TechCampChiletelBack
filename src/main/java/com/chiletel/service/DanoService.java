package com.chiletel.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chiletel.dto.DanoNuevoDTO;
import com.chiletel.dto.DanoVerReporteDTO;
import com.chiletel.entity.Cliente;
import com.chiletel.entity.Dano;
import com.chiletel.entity.OrdenAtencion;
import com.chiletel.entity.TipoDano;
import com.chiletel.exceptionHandler.NotFoundException;
import com.chiletel.mapper.DanoMapper;
import com.chiletel.repository.IClienteRepository;
import com.chiletel.repository.IDanoRepository;
import com.chiletel.repository.IOrdenAtencionRepository;
import com.chiletel.repository.ITipoDanoRepository;

/**
 * <h2>Descrcipción:</h2>
 * Clase encargada de implementar los metodos definidos en {@link IDanoService}
 * @author Brayan Baquero
 */
@Service
public class DanoService implements IDanoService {
	
	@Autowired
	private IDanoRepository danoRepo;
	@Autowired
	private IClienteRepository clienteRepo;
	@Autowired
	private ITipoDanoRepository tipoDanoRepo;
	@Autowired
	private IOrdenAtencionRepository ordenAtencionRepo;
	@Autowired 
	private DanoMapper danoMapper;

	@Transactional
	@Override
	public void addDano(DanoNuevoDTO danoDTO) {
		Cliente cliente=clienteRepo.findByNumeroIden(danoDTO.getCliente())
				.orElseThrow(()->new NotFoundException("El cliente no existe"));
		TipoDano tipoDano=tipoDanoRepo.findByNombre(danoDTO.getTipoDano())
				.orElseThrow(()->new NotFoundException("El tipo de daño a reportar no existe."));
		Dano dano=danoMapper.toEntity(danoDTO);
		dano.setCliente(cliente);
		dano.setTipoDano(tipoDano);
		OrdenAtencion ordenAtencion=new OrdenAtencion();
		ordenAtencion.setDano(danoRepo.save(dano));
		//ordenAtencion.setNumOrden(UUID.randomUUID().toString());
		ordenAtencionRepo.save(ordenAtencion);	
	}

	@Override
	public Page<DanoVerReporteDTO> getDanos(Pageable pageable) {
		Page<Dano> danoVRPage=danoRepo.findAll(pageable);
		List<DanoVerReporteDTO> danoVRDto=danoMapper.toDtoDanoVerReportes(danoVRPage.getContent());
		return new PageImpl<DanoVerReporteDTO>(danoVRDto,pageable,danoVRPage.getTotalElements());
	}
}
