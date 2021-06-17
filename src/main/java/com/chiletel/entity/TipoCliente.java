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
@Table(name = "TIPOS_CLIENTE")
public class TipoCliente {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "tipos_cliente_seq", allocationSize = 1, name = "CUST_SEQ")
	@Column(name = "ID_TIPO_CLIENTE")
	private Integer id;
	@Column(name = "NOMBRE")
	private String tipo;
	@Column(name = "PRIORIDAD")
	private int prioridad;
}
