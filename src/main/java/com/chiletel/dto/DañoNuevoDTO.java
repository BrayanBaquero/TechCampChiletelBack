package com.chiletel.dto;

import java.math.BigInteger;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Da�oNuevoDTO {
	private BigInteger cliente;
	private String tipoDa�o;
	private String descripcion;
	

}
