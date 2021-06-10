package com.chiletel.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "AGENDA")
public class Agenda {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "AGENDA_SEQ", allocationSize = 1, name = "CUST_SEQ")
	@Column(name="AGENDA_ID")
	private int id;
	@Column(name = "ORDEN_ATENCION_ID")
	private int ordenAtencion;
	@Column(name = "TECNICO_ID")
	private int tecnico;
	@Column(name = "H_INICIO")
	private Date horaInicio;
	@Column(name = "H_FINAL")
	private Date horaFinal;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA",nullable = false)
	private Date fecha;
}
