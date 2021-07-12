package com.chiletel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 @Data
 @NoArgsConstructor
 @AllArgsConstructor
public class OrdenAtencionDTO {
	private int numOrden;
	private long cliente;
	private String incidencia;
}
