package com.chiletel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chiletel.dto.TipoDa�oDTO;
import com.chiletel.entity.TipoDa�o;
import com.chiletel.exceptionHandler.BadRequestException;
import com.chiletel.exceptionHandler.NotFoundException;
import com.chiletel.mapper.TipoDa�oMapper;
import com.chiletel.repository.ITipoDa�oRepository;

/**
 * <h2>Descrcipci�n:</h2>
 * Clase encargada de implementar los metodos definidos en {@link ITipoDa�oService}
 * @author Brayan Baquero
 */
@Service
@Transactional
public class TipoDa�oService implements ITipoDa�oService{
	@Autowired
	private ITipoDa�oRepository tipoDa�oRepo;
	@Autowired
	private TipoDa�oMapper tipoDa�oMapper;
	
	@Override
	public List<TipoDa�oDTO> getAllTipoDa�o() {
		List<TipoDa�o> tipoDa�os=tipoDa�oRepo.findAll();
		if(tipoDa�os.isEmpty())
			throw new NotFoundException("No hay ningun tipo de da�o.");
		return tipoDa�oMapper.toDtos(tipoDa�os);
	}

	@Override
	public void updateTipoDa�o(List<TipoDa�oDTO> tipoDa�oDTOs) {
		tipoDa�oRepo.updateTDa�oPrioridadNull();
		try {
			tipoDa�oDTOs.forEach(tDa�o->{
				int actualizado=tipoDa�oRepo.updateTDa�o(tDa�o.getNombre(),tDa�o.getTiempo(),tDa�o.getPrioridad());
				/**Se valida si algun registro no se actualizo por que el parametro de busqueda no coincidia
				 * y lanza una excepcion lo cual, tras lo cual gracias al @transactional definido en la clase
				 *  se hace rollback para no persistir lo registros ya actualizados
				*/
				if(actualizado!=1 || tDa�o.getPrioridad()<1 || tDa�o.getTiempo()<1) {
					throw new BadRequestException("Error al actualizar los datos.");
				}
			});
			
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("La prioridad debe ser unica");
		}
		
	}

	

}
