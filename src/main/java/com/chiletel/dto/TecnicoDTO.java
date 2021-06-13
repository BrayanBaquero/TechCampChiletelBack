package com.chiletel.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TecnicoDTO {
	@ApiModelProperty(position = 0)
	private String nombre;
	@ApiModelProperty(position = 1)
	private String apellido;
	@ApiModelProperty(position = 2)
	private String numeroIdentificacion;
	@ApiModelProperty(position = 3)
	private String email;
	@ApiModelProperty(position = 4)
	private String telefono;
	@ApiModelProperty(position = 5)
	private String cuadrilla;
}
