package com.chiletel.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class DetallesAgendaDTO {
	private String ordenAtencion;
	private int horaInicio;
	private int horaFinal;
	private Date fecha;
}
