package com.chiletel.dto;

import java.math.BigInteger;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

 @Data
public class OrdenAtencionDTO {
	private String numOrden;
	private BigInteger cliente;
	private String incidencia;
}
