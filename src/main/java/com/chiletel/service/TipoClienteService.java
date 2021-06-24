package com.chiletel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chiletel.dto.TipoClienteDTO;
import com.chiletel.entity.TipoCliente;
import com.chiletel.exceptionHandler.BadRequestException;
import com.chiletel.exceptionHandler.NotFoundException;
import com.chiletel.mapper.TipoClienteMapper;
import com.chiletel.repository.ITipoClienteRepository;

@Service
public class TipoClienteService implements ITipoClienteService {
	
	@Autowired
	private ITipoClienteRepository tipoClienteRepo;
	@Autowired
	private TipoClienteMapper tipoClienteMapper;

	@Override
	public List<TipoClienteDTO> getAllTipoCliente() {
		List<TipoCliente> tipoDaños=tipoClienteRepo.findAll();
		if(tipoDaños.isEmpty())
			throw new NotFoundException("No hay ningun tipo de cliente");
		return tipoClienteMapper.toDtos(tipoDaños);
	}
	
	@Transactional
	@Override
	public void Update(List<TipoClienteDTO> tClienteDTOs) {
		tipoClienteRepo.updateTClientePrioridadNull();
		try {
			tClienteDTOs.forEach(tCliente->{
				int actualizado=tipoClienteRepo.updateTClientePrioridad(tCliente.getNombre(),tCliente.getPrioridad());
				/**Se valida si algun registro no se actualizo por que el parametro de busqueda no coincidia
				 * y lanza una excepcion lo cual, tras lo cual gracias al @transactional definido en la clase
				 *  se hace rollback para no persistir lo registros ya actualizados
				*/
				if(actualizado!=1) {
					throw new BadRequestException("Error al actualizar los datos");
				}
			});
			
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("La prioridad debe ser unica");
		}
		
	}

	
	

}
