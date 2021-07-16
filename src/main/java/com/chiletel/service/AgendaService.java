package com.chiletel.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chiletel.dao.IAgendaDao;
import com.chiletel.dto.AgendaEventosDTO;
import com.chiletel.dto.AgendaTecnicosDTO;
import com.chiletel.exceptionHandler.BadRequestException;
import com.chiletel.repository.ITecnicoRepository;

/**
 * <h2>Descripción:<h2>
 * Clase encargada de implementar los metodos definidos en la interface {@link IAgendaService}.
 * @author Brayan Baquero
 *
 */
@Service
public class AgendaService implements IAgendaService {
	
	@Autowired
	private ITecnicoRepository tecnicoRepo;
	@Autowired
	private IAgendaDao agendaDao;

	@Override
	public Page<AgendaTecnicosDTO> getAll(Pageable pageable) {
		return tecnicoRepo.findAllBy(pageable);
	}

	@Override
	public List<AgendaEventosDTO> getAgendaTecnico(Long ident,Date fecha_inicio,Date fecha_final) {
		if(!fecha_inicio.before(fecha_final))
			throw new BadRequestException("fecha_inicio no puede ser mayor que fecha_final");
		//List<AgendaEventosDTO> agendaDetallesDTOs=agendaRepo.findAgendaTec(ident,fecha_inicio,fecha_final);
		//return agendaDetallesDTOs;
		return agendaDao.getAllAgendaEventos(ident, fecha_inicio, fecha_final);
	}

	@Override
	public int genAgenda() {
		return agendaDao.agendarOrdenes();
	}

}
