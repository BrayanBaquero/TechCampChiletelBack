package com.chiletel.service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chiletel.dao.AgendaSpDao;
import com.chiletel.dto.AgendaDTO;
import com.chiletel.dto.AgendaEventosDTO;
import com.chiletel.entity.Tecnico;
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
	public Page<AgendaDTO> getAll(Pageable pageable) {
		Page<Tecnico> tecnicos=tecnicoRepo.getAllTecnicosActivos(pageable);
		List<AgendaDTO> agendaDTOs=new ArrayList<AgendaDTO>();
		//Mapear AgendaDTO
		tecnicos.getContent().forEach(el->{
			List<AgendaEventosDTO> ag=agendaRepo.findAllAgTec(el.getNumeroIden());
			agendaDTOs.add(new AgendaDTO(el.getNombre(),el.getNumeroIden(),ag));
		});
		return new PageImpl<AgendaDTO>(agendaDTOs,pageable,tecnicos.getTotalElements());
	}

	@Override
	public List<AgendaEventosDTO> getAgendaTecnico(BigInteger ident,Date fecha_inicio,Date fecha_final) {
		if(!fecha_inicio.before(fecha_final))
			throw new BadRequestException("fecha_inicio no puede ser mayor que fecha_final");
		
		SimpleDateFormat formato=new SimpleDateFormat("dd/MM/YYY");
		String fi=formato.format(fecha_inicio);
		System.out.println("fecha inicio: "+fi+ "  Fecha final: "+fecha_final);
		List<AgendaEventosDTO> agendaDetallesDTOs=agendaRepo.findAgendaTec(ident,fecha_inicio,fecha_final);
		return agendaDetallesDTOs;
	}

	@Override
	public int genAgenda() {
		return agendaSpDao.agendarOrdenes();
	}



}
