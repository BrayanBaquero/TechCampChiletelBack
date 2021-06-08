package com.chiletel.entity;

import javax.annotation.Generated;
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
@Table(name = "CLIENTE")
public class Cliente {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "cliente_seq", allocationSize = 1, name = "CUST_SEQ")
	private Integer id;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name="APELLIDO")
	private String apellido;
	@Column(name = "TIPO_CLIENTE")
	private int tipoCliente;
	@Column(name = "NID")
	private String nid;
	@Column(name = "email")
	private String email;
	@Column(name = "TELEFONO")
	private String telefono;
	@Column(name = "DIRECCION")
	private String direccion;
	@Column(name = "ZONA")
	private int zona;
	

}
