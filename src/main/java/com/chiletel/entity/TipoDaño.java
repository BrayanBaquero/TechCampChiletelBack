package com.chiletel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
//@Table(name = "TIPOS_INCIDENCIA")
@Table(
		name = "TIPOS_INCIDENCIA",
		uniqueConstraints = { @UniqueConstraint(name = "UK_TIPOS_INCIDENCIA_01", columnNames = { "prioridad"})}
	)
public class TipoDa?o {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "TIPOS_INCIDENCIA_SEQ")
    @SequenceGenerator(sequenceName = "TIPOS_INCIDENCIA_SEQ", allocationSize = 1, name = "TIPOS_INCIDENCIA_SEQ")
	@Column(name="ID_TIPO_INCIDENCIA")
	private int id;
	@Column(name = "NOMBRE",length = 40)
	private String nombre;
	@Column(name = "PRIORIDAD")
	private int prioridad;
	//@CreationTimestamp
	//@Temporal(TemporalType.DATE)
	@Column(name = "TIEMPO_ATENCION",nullable = false)
	//private Date tiempoAtencion;
	private int tiempoAtencion;
}
