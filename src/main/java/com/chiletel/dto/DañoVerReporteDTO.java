package com.chiletel.dto;

import java.math.BigInteger;
import java.util.Date;

import lombok.Data;

@Data
public class DañoVerReporteDTO {
	private BigInteger numeroIden;
	private String tipoDano;
	private Date fecha;
}
