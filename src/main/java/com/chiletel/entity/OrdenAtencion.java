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
@Table(name = "ORDEN_ATENCION")
public class OrdenAtencion {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "ORDEN_ATENCION_SEQ", allocationSize = 1, name = "CUST_SEQ")
	@Column(name="ORDEN_ATENCION")
	private int id;
	@Column(name = "DA�O")
	private int da�o;
	@Column(name = "TIPO_DA�O")
	private int tipoDa�o;
	@Temporal(TemporalType.TIME)
	@Column(name = "TIEMPO")
	private Date tiempo;
}
