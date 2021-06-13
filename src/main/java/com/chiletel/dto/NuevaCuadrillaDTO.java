package com.chiletel.dto;

import java.util.Set;

import com.chiletel.entity.Zona;

import lombok.Data;

@Data
public class NuevaCuadrillaDTO {
	private String nombre;
	private Set<Zona> zona;
}
