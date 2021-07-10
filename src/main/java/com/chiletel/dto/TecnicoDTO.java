package com.chiletel.dto;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TecnicoDTO {
	
	private String nombre;
	private String apellido;
	private BigInteger numeroIden;
	@Email(message = "Formato de correo electronico no valido")
	private String email;
	private String telefono;
	private String direccion;
	private String cuadrilla;
	private Set<String> tDano=new HashSet<>();


	
	
}
