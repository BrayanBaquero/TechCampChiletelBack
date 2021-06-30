package com.chiletel.dto;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DañoNuevoDTO {
	private int cliente;
	private String tipoDaño;
	private String descripcion;
	

}
