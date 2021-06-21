package com.chiletel.dto;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.chiletel.entity.Agenda;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class AgendaDTO {
	
	private String tecnico;
	
	private String identificacion;
	
	private Set<DetallesAgendaDTO> detalles;

}
