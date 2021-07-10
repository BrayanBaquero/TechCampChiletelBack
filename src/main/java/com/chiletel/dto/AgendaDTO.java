package com.chiletel.dto;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.chiletel.entity.Agenda;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AgendaDTO {
	
	private String tecnico;
	
	private BigInteger identificacion;
	
	private List<AgendaEventosDTO> detalles;

}
