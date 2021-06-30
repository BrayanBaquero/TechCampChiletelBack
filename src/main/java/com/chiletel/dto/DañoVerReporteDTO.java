package com.chiletel.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DañoVerReporteDTO {
	private int numeroIden;
	private String tipoDaño;
	private Date fecha;
}
