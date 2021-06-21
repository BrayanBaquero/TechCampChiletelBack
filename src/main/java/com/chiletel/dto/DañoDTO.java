package com.chiletel.dto;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DañoDTO {
	private int cliente;
	private int tipoDaño;
	private String descripcion;
	

}
