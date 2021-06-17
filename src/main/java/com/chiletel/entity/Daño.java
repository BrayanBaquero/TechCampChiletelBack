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
@Table(name = "INCIDENCIAS")
public class Daño {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "INCIDENCIAS_SEG", allocationSize = 1, name = "CUST_SEQ")
	@Column(name="ID_INCIDENCIA")
	private int id;
	@Column(name = "ID_CLIENTE")
	private int cliente;
	@Column(name = "ID_TIPO_INCIDENCIA")
	private int tipoDaño;
	@Column(name = "DESCRIPCION",length = 200)
	private String descripcion;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "RECHA_REGISTRO",nullable = false)
	private Date fechaRegistro;
}
