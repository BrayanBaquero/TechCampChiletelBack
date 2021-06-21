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
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "tipos_cliente_seq")
    @SequenceGenerator(sequenceName = "tipos_cliente_seq", allocationSize = 1, name = "tipos_cliente_seq")
	@Column(name = "ID_TIPO_CLIENTE")
	private Integer id;
	@Column(name = "NOMBRE",length = 40)
	private String tipo;
	@Column(name = "PRIORIDAD")
	private int prioridad;
}
