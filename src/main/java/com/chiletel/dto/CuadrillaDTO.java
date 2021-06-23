package com.chiletel.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.chiletel.entity.Zona;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CuadrillaDTO {
	private String nombre;
	private int miembros;
	private Set<String> zona=new HashSet<>();
}
