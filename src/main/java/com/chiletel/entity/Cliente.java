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
@Table(name = "CLIENTES")
public class Cliente {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "clientes_seq", allocationSize = 1, name = "CUST_SEQ")
	@Column(name="ID_CLIENTE")
	private Integer id;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name="APELLIDO")
	private String apellido;
	@Column(name = "IDENTIFICACION")
	private int numeroIden;
	@Column(name = "ID_TIPO_CLIENTE")
	private int tipoCliente;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "TELEFONO")
	private String telefono;
	@Column(name = "DIRECCION")
	private String direccion;
	@Column(name = "ID_ZONA")
	private int zona;
	

}
