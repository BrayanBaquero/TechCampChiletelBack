package com.chiletel.dto;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Da�oDTO {
	@ApiModelProperty(position = 0)
	private int cliente;
	@ApiModelProperty(position = 1)
	private int tipoDa�o;
	@ApiModelProperty(position = 2)
	private String descripcion;
	@ApiModelProperty(position = 3)
	private boolean estado;
	@ApiModelProperty(position = 4)
	private Date fechaRegistro;

}
