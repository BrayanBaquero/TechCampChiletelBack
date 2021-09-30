package com.chiletel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chiletel.dto.TipoDanoDTO;
import com.chiletel.entity.TipoDano;
import com.chiletel.exceptionHandler.BadRequestException;
import com.chiletel.exceptionHandler.NotFoundException;
import com.chiletel.mapper.TipoDanoMapper;
import com.chiletel.repository.ITipoDanoRepository;

/**
 * <h2>Descrcipción:</h2>
 * Clase encargada de implementar los metodos definidos en {@link ITipoDanoService}
 * @author Brayan Baquero
 */
@Service
@Transactional
public class TipoDanoService implements ITipoDanoService{
	@Autowired
	private ITipoDanoRepository tipoDanoRepo;
	@Autowired
	private TipoDanoMapper tipoDanoMapper;
	
	@Override
	public List<TipoDanoDTO> getAllTipoDano() {
		List<TipoDano> tipoDanos=tipoDanoRepo.findAll();
		if(tipoDanos.isEmpty())
			throw new NotFoundException("No hay ningun tipo de daño.");
		return tipoDanoMapper.toDtos(tipoDanos);
	}

	@Override
	public void updateTipoDano(List<TipoDanoDTO> tipoDanoDTOs) {
		tipoDanoRepo.updateTDanoPrioridadNull();
		try {
			tipoDanoDTOs.forEach(tDano->{
				int actualizado=tipoDanoRepo.updateTDano(tDano.getNombre(),tDano.getTiempo(),tDano.getPrioridad());
				/**Se valida si algun registro no se actualizo por que el parametro de busqueda no coincidia
				 * y lanza una excepcion lo cual, tras lo cual gracias al @transactional definido en la clase
				 *  se hace rollback para no persistir lo registros ya actualizados
				*/
				if(actualizado!=1 || tDano.getPrioridad()<1 || tDano.getTiempo()<1) {
					throw new BadRequestException("Error al actualizar los datos.");
				}
			});
			
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("La prioridad debe ser unica");
		}
		
	}

	

}
