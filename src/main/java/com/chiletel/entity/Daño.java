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
@Table(name = "DAÑO")
public class Daño {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "DAÑO_SEQ", allocationSize = 1, name = "CUST_SEQ")
	@Column(name="DAÑO_ID")
	private int id;
	@Column(name = "CLIENTE_ID")
	private int cliente;
	@Column(name = "TIPO_DAÑO_ID")
	private int tipoDaño;
	@Column(name = "DESCRIPCION",length = 200)
	private String descripcion;
	@Column(name = "ESTADO")
	private boolean estado;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RECHA_REGISTRO",nullable = false)
	private Date fechaRegistro;
}
