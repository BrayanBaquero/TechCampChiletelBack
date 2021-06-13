package com.chiletel.dto;

import java.util.Set;

import com.chiletel.entity.Zona;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CuadrillaDTO {
	@ApiModelProperty(position = 0)
	private String nombre;
	@ApiModelProperty(position = 1)
	private int mienbros;
	@ApiModelProperty(position = 2)
	private Set<Zona> zona;
}
