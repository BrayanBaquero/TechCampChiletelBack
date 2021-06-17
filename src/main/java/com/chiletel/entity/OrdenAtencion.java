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

import lombok.Data;

@Data
@Entity
@Table(name = "ORDENES_ATENCION")
public class OrdenAtencion {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "ORDENES_ATENCION_SEQ", allocationSize = 1, name = "CUST_SEQ")
	@Column(name="ID_ORDEN_ATENCION")
	private int id;
	@Column(name = "ID_INCIDENCIA")
	private int daño;
	@Column(name = "NUMERO_ORDEN")
	private String numOrden;
	@Column(name = "AGENDADO",columnDefinition = "false")
	private boolean tiempo;
}
