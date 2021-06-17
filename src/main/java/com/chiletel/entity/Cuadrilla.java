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
@Table(name = "CUADRILLAS")

public class Cuadrilla {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "CUADRILLAS_SEQ", allocationSize = 1, name = "CUST_SEQ")
	@Column(name = "ID_CUADRILLA")
	private String id;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "BORRADO",columnDefinition = "false")
	private boolean borrado;
}
