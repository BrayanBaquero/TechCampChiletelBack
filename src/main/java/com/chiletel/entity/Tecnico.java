package com.chiletel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TECNICO")
public class Tecnico {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "TECNICO_SEQ", allocationSize = 1, name = "CUST_SEQ")
	@Column(name="TECNICO_ID")
	private int id;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "APELLIDO")
	private String apellido;
	@Column(name = "INDENTIFICACION")
	private String numeroIden;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "TELEFONO")
	private String telefono;
	@Column(name = "DIRECCION")
	private String direccion;
	@Column(name = "CUADRILLA_ID")
	private int cuadrilla;

}
