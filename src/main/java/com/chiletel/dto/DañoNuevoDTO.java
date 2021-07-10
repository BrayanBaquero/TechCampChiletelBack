package com.chiletel.dto;

import java.math.BigInteger;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DañoNuevoDTO {
	private BigInteger cliente;
	private String tipoDaño;
	private String descripcion;
	

}
