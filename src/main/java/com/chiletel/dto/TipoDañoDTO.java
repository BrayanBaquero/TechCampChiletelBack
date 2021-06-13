package com.chiletel.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TipoDañoDTO {
	@ApiModelProperty(position = 0)
	private String nombre;
	@ApiModelProperty(position = 1)
	private int prioridad;
	@ApiModelProperty(position = 2)
	private int tiempo;
}
