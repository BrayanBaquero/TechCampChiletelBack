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
@Table(name = "TIPO_DAÑO")
public class TipoDaño {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "TIPO_DAÑO_SEQ", allocationSize = 1, name = "CUST_SEQ")
	@Column(name="TIPO_DAÑO_ID")
	private int id;
	@Column(name = "NOMBRE",length = 20)
	private String nombre;
	@Column(name = "PRIORIDAD")
	private String prioridad;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_REPORTE",nullable = false)
	private Date fechaReporte;
}
