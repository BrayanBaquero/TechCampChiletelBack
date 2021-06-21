package com.chiletel.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

 @Data
public class OrdenAtencionDTO {
	private String numOrden;
	private int cliente;
	private String daño;
}
