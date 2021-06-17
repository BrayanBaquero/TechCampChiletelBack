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
@Table(name = "TECNICOS")
public class Tecnico {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "TECNICOS_SEQ", allocationSize = 1, name = "CUST_SEQ")
	@Column(name="ID_TECNICO")
	private int id;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "APELLIDO")
	private String apellido;
	@Column(name = "INDENTIFICACION",unique = true)
	private int numeroIden;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "TELEFONO")
	private String telefono;
	@Column(name = "DIRECCION")
	private String direccion;
	@Column(name = "ID_CUADRILLA")
	private int cuadrilla;
	@Column(name ="BORRADO",columnDefinition = "false")
	private boolean borrado;

}
