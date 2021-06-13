package com.chiletel.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

 @Data
public class OrdenAtencionDTO {
	@ApiModelProperty(position = 0)
	private String numOrden;
	@ApiModelProperty(position = 1)
	private int cliente;
	@ApiModelProperty(position = 2)
	private String daño;
}
