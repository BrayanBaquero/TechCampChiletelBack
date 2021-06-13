package com.chiletel.dto;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.chiletel.entity.Agenda;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AgendaDTO {
	@ApiModelProperty(position = 0)
	private String tecnico;
	@ApiModelProperty(position = 1)
	private Set<Agenda> horas;

}
