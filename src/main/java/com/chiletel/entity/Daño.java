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
@Table(name = "DA�O")
public class Da�o {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "DA�O_SEQ", allocationSize = 1, name = "CUST_SEQ")
	@Column(name="DA�O_ID")
	private int id;
	@Column(name = "CLIENTE_ID")
	private int cliente;
	@Column(name = "TIPO_DA�O_ID")
	private int tipoDa�o;
	@Column(name = "DESCRIPCION",length = 200)
	private String descripcion;
	@Column(name = "ESTADO")
	private boolean estado;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RECHA_REGISTRO",nullable = false)
	private Date fechaRegistro;
}
