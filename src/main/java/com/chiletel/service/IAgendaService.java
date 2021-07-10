package com.chiletel.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chiletel.dto.AgendaDTO;
import com.chiletel.dto.AgendaEventosDTO;
import com.chiletel.entity.Agenda;

public interface IAgendaService {
	public Page<AgendaDTO> getAll(Pageable pageable);
	
	public List<AgendaEventosDTO> getAgendaTecnico (BigInteger ident,Date fecha_inicio,Date fecha_final);
	
	public int genAgenda();
}
