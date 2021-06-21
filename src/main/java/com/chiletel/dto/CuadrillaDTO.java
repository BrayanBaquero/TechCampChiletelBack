package com.chiletel.dto;

import java.util.Set;

import com.chiletel.entity.Zona;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CuadrillaDTO {
	private String nombre;
	private int miembros;
	private Set<Zona> zona;
}
