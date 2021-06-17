package com.chiletel.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
@ApiModel
public class TecnicoDTO {
	@ApiModelProperty(position = 1)
	private String nombre;
	@ApiModelProperty(position = 2)
	private String apellido;
	@ApiModelProperty(position = 3)
	private String numeroIdentificacion;
	@ApiModelProperty(position = 4)
	private String email;
	@ApiModelProperty(position = 5)
	private String telefono;
	@ApiModelProperty(position = 6)
	private String cuadrilla;
}
