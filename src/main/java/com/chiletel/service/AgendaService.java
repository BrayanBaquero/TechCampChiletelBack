package com.chiletel.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chiletel.dao.AgendaSpDao;
import com.chiletel.dto.AgendaEventosDTO;
import com.chiletel.dto.AgendaEventosDTOO;
import com.chiletel.dto.AgendaTecnicosDTO;
import com.chiletel.exceptionHandler.BadRequestException;
import com.chiletel.repository.IAgendaRepository;
import com.chiletel.repository.ITecnicoRepository;

@Service
public class AgendaService implements IAgendaService {
	
	@Autowired
	private IAgendaRepository agendaRepo;
	@Autowired
	private ITecnicoRepository tecnicoRepo;
	@Autowired
	private AgendaSpDao agendaSpDao;

	@Override
	public Page<AgendaTecnicosDTO> getAll(Pageable pageable) {
		return tecnicoRepo.findAllBy(pageable);
	}

	@Override
	public List<AgendaEventosDTOO> getAgendaTecnico(Long ident,Date fecha_inicio,Date fecha_final) {
		if(!fecha_inicio.before(fecha_final))
			throw new BadRequestException("fecha_inicio no puede ser mayor que fecha_final");
		
		SimpleDateFormat formato=new SimpleDateFormat("dd/MM/YYY");
		String fi=formato.format(fecha_inicio);
		//System.out.println("fecha inicio: "+fi+ "  Fecha final: "+fecha_final);
		List<AgendaEventosDTO> agendaDetallesDTOs=agendaRepo.findAgendaTec(ident,fecha_inicio,fecha_final);
		
		agendaSpDao.getAllAgendaEventos(ident, fecha_inicio, fecha_final).forEach(el->{
		 System.out.println(el.getNombreCliente());
		});
		//return agendaDetallesDTOs;
		return agendaSpDao.getAllAgendaEventos(ident, fecha_inicio, fecha_final);
	}

	@Override
	public int genAgenda() {
		return agendaSpDao.agendarOrdenes();
	}

}
