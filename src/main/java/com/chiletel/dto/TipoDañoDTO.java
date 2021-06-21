package com.chiletel.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TipoDañoDTO {
	private String nombre;
	private int prioridad;
	private int tiempo;
}
