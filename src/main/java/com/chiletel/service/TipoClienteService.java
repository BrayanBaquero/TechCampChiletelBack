package com.chiletel.service;

import java.util.List;

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
/**
 * <h2>Descrcipción:</h2>
 * Clase encargada de implementar los metodos definidos en {@link ITipoClienteService}
 * @author Brayan Baquero
 */
@Service
public class TipoClienteService implements ITipoClienteService {
	
	@Autowired
	private ITipoClienteRepository tipoClienteRepo;
	@Autowired
	private TipoClienteMapper tipoClienteMapper;

	@Override
	public List<TipoClienteDTO> getAllTipoCliente() {
		List<TipoCliente> tipoDanos=tipoClienteRepo.findAll();
		if(tipoDanos.isEmpty())
			throw new NotFoundException("No hay ningun tipo de cliente");
		return tipoClienteMapper.toDtos(tipoDanos);
	}
	
	@Transactional
	@Override
	public void updateTipoCliente(List<TipoClienteDTO> tClienteDTOs) {
		tipoClienteRepo.updateTClientePrioridadNull();
		try {
			tClienteDTOs.forEach(tCliente->{
				int actualizado=tipoClienteRepo.updateTClientePrioridad(tCliente.getNombre(),tCliente.getPrioridad());
				/**Se valida si algun registro no se actualizo por que el parametro de busqueda no coincidia
				 * y lanza una excepcion lo cual, tras lo cual gracias al @transactional definido en la clase
				 *  se hace rollback para no persistir lo registros ya actualizados
				*/
				if(actualizado!=1 || tCliente.getPrioridad()<1) {
					throw new BadRequestException("Error al actualizar los datos");
				}
			});
			
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("La prioridad debe ser unica");
		}
		
	}

	
	

}
