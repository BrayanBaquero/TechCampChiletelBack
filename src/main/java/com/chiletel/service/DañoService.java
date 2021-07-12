package com.chiletel.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

/**
 * <h2>Descrcipción:</h2>
 * Clase encargada de implementar los metodos definidos en {@link IDañoService}
 * @author Brayan Baquero
 */
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

	@Transactional
	@Override
	public void addDaño(DañoNuevoDTO dañoDTO) {
		Cliente cliente=clienteRepo.findByNumeroIden(dañoDTO.getCliente())
				.orElseThrow(()->new NotFoundException("El cliente no existe"));
		TipoDaño tipoDaño=tipoDañoRepo.findByNombre(dañoDTO.getTipoDaño())
				.orElseThrow(()->new NotFoundException("El tipo de daño a reportar no existe."));
		Daño daño=dañoMapper.toEntity(dañoDTO);
		daño.setCliente(cliente);
		daño.setTipoDaño(tipoDaño);
		OrdenAtencion ordenAtencion=new OrdenAtencion();
		ordenAtencion.setDaño(dañoRepo.save(daño));
		//ordenAtencion.setNumOrden(UUID.randomUUID().toString());
		ordenAtencionRepo.save(ordenAtencion);	
	}

	@Override
	public Page<DañoVerReporteDTO> getDaños(Pageable pageable) {
		Page<Daño> dañoVRPage=dañoRepo.findAll(pageable);
		List<DañoVerReporteDTO> dañoVRDto=dañoMapper.toDtoDañoVerReportes(dañoVRPage.getContent());
		return new PageImpl<DañoVerReporteDTO>(dañoVRDto,pageable,dañoVRPage.getTotalElements());
	}
}
