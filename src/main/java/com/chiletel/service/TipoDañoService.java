package com.chiletel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chiletel.dto.TipoDañoDTO;
import com.chiletel.entity.TipoDaño;
import com.chiletel.exceptionHandler.BadRequestException;
import com.chiletel.exceptionHandler.NotFoundException;
import com.chiletel.mapper.TipoDañoMapper;
import com.chiletel.repository.ITipoDañoRepository;

/**
 * <h2>Descrcipción:</h2>
 * Clase encargada de implementar los metodos definidos en {@link ITipoDañoService}
 * @author Brayan Baquero
 */
@Service
@Transactional
public class TipoDañoService implements ITipoDañoService{
	@Autowired
	private ITipoDañoRepository tipoDañoRepo;
	@Autowired
	private TipoDañoMapper tipoDañoMapper;
	
	@Override
	public List<TipoDañoDTO> getAllTipoDaño() {
		List<TipoDaño> tipoDaños=tipoDañoRepo.findAll();
		if(tipoDaños.isEmpty())
			throw new NotFoundException("No hay ningun tipo de daño.");
		return tipoDañoMapper.toDtos(tipoDaños);
	}

	@Override
	public void updateTipoDaño(List<TipoDañoDTO> tipoDañoDTOs) {
		tipoDañoRepo.updateTDañoPrioridadNull();
		try {
			tipoDañoDTOs.forEach(tDaño->{
				int actualizado=tipoDañoRepo.updateTDaño(tDaño.getNombre(),tDaño.getTiempo(),tDaño.getPrioridad());
				/**Se valida si algun registro no se actualizo por que el parametro de busqueda no coincidia
				 * y lanza una excepcion lo cual, tras lo cual gracias al @transactional definido en la clase
				 *  se hace rollback para no persistir lo registros ya actualizados
				*/
				if(actualizado!=1 || tDaño.getPrioridad()<1 || tDaño.getTiempo()<1) {
					throw new BadRequestException("Error al actualizar los datos.");
				}
			});
			
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("La prioridad debe ser unica");
		}
		
	}

	

}
